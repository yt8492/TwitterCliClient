import java.net.URLEncoder

fun String.encodeUrl(): String = URLEncoder.encode(this, "UTF-8")


fun getEnv(name: String): String = System.getenv(name) ?: run {
    error("environment variable $name must not be null")
}