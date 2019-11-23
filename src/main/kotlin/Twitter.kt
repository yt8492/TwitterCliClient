import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class Twitter(private val oAuth: OAuth) {
    fun tweet(text: String) {
        val request = mapOf(
            "status" to text.encodeUrl()
        )
        val param = oAuth.buildAuthHeaderParams(
            "POST",
            "https://api.twitter.com/1.1/statuses/update.json",
            request
        ).toSortedMap()
        val paramStr = param.map {
            """${it.key}="${it.value.encodeUrl()}""""
        }.joinToString(", ")
        val requestStr = request.map {
            "${it.key}=${it.value}"
        }.joinToString("&")
        val authHeader = "OAuth $paramStr"
        val url = URL("https://api.twitter.com/1.1/statuses/update.json?$requestStr")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.setRequestProperty("Authorization", authHeader)
        try {
            connection.connect()
            connection.inputStream.bufferedReader().readLines()
            println("Success")
        } catch (e: IOException) {
            e.printStackTrace()
            println(connection.errorStream.bufferedReader().readLines().joinToString("\n"))
        }
    }
}