import kotlinx.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class Twitter(private val oAuth: OAuth) {
    fun tweet(text: String) {
        val request = mapOf(
            "status" to text
        )
        val param = oAuth.buildAuthHeaderParams(
            "POST",
            "https://api.twitter.com/1.1/statuses/update.json",
            request
        ).toSortedMap()
        val paramStr = param.map {
            """${it.key}="${it.value.encodeUrl()}""""
        }.joinToString(", ")
        println(paramStr)
        val requestStr = request.map {
            "${it.key}=${it.value.encodeUrl()}"
        }.joinToString("&")
        val authHeader = "OAuth $paramStr"
        val url = URL("https://api.twitter.com/1.1/statuses/update.json?$requestStr")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.setRequestProperty("Authorization", authHeader)
        try {
            connection.connect()
            val reader = connection.inputStream.bufferedReader()
            println(reader.readLines().joinToString("\n"))
        } catch (e: IOException) {
            e.printStackTrace()
            println(connection.errorStream.bufferedReader().readLines().joinToString("\n"))
        }
    }
}