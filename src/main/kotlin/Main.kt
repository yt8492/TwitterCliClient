fun main() {
    val consumerKey = getEnv("TW_CONSUMER_KEY")
    val consumerSecret = getEnv("TW_CONSUMER_SECRET")
    val oauthToken = getEnv("TW_OAUTH_TOKEN")
    val oauthSecret = getEnv("TW_OAUTH_SECRET")
    val oAuth = OAuth(
        consumerKey,
        consumerSecret,
        oauthToken,
        oauthSecret
    )
    print("Tweet: ")
    val status = readLine() ?: return
    Twitter(oAuth).tweet(status)
}