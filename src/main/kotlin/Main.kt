import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.util.KtorExperimentalAPI
import json.TokenResponse
import kotlinx.coroutines.runBlocking
import java.io.Console

@KtorExperimentalAPI
fun main() = runBlocking {
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
    val httpClient = HttpClient(CIO) {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }
    httpClient.use { client ->
        val tokenResponse: TokenResponse = client.get("https://api.twitter.com/oauth2/token") {

        }
    }
}