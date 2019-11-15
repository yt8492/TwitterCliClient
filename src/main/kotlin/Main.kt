import api.json.TokenResponse
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.http.Parameters
import io.ktor.util.KtorExperimentalAPI
import kotlinx.coroutines.runBlocking
import java.io.Console
import java.util.*

@KtorExperimentalAPI
fun main() = runBlocking {
    val httpClient = HttpClient(CIO) {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }
    httpClient.use { client ->
        val tokenResponse: TokenResponse = client.post("https://api.twitter.com/oauth2/token") {
            val username = "username"
            val password = "password"
            val encoded = Base64.getEncoder().encodeToString("$username:$password".toByteArray())
            header("Authorization", "Basic $encoded")
            body = FormDataContent(Parameters.build {
                append("grant_type", "client_credentials")
            })
        }
        println(tokenResponse.accessToken)
    }
    val console: Console? = System.console()
    print("UserId: ")
    val userId = console?.readLine() ?: readLine() ?: run {
        println("UserId must not be null")
        return@runBlocking
    }
    print("Password: ")
    val password = console?.readPassword() ?: readLine() ?: run {
        println("Password must not be null")
        return@runBlocking
    }
}