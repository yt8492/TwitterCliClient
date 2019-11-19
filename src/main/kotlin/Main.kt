fun main() {
    val oAuth = OAuth(
        "",
        "",
        "",
        ""
    )
    Twitter(oAuth).tweet(readLine() ?: "test")
}