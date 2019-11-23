import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class OAuth(
    private val consumerKey: String,
    private val consumerSecret: String,
    private val oauthToken: String,
    private val oauthTokenSecret: String
) {
    fun buildAuthHeaderParams(
        method: String,
        url: String,
        requestParams: Map<String, String>
    ): Map<String, String> {
        val params = mapOf(
            "oauth_consumer_key" to consumerKey,
            "oauth_nonce" to UUID.randomUUID().toString().replace("-", ""),
            "oauth_signature_method" to "HMAC-SHA1",
            "oauth_timestamp" to "${Date().time / 1000}",
            "oauth_token" to oauthToken,
            "oauth_version" to "1.0"
        )
        val paramList = (params + requestParams).map {
            "${it.key}=${it.value}"
        }.sorted()
        val requestValue = listOf(method, url.encodeUrl(), paramList.joinToString("&").encodeUrl()).joinToString("&")
        val key = "${consumerSecret.encodeUrl()}&${oauthTokenSecret.encodeUrl()}"
        val signingKey = SecretKeySpec(key.toByteArray(), "HmacSHA1")
        val mac = Mac.getInstance(signingKey.algorithm).apply {
            init(signingKey)
        }
        val rawHmac = mac.doFinal(requestValue.toByteArray())
        val signature = Base64.getEncoder().encodeToString(rawHmac)
        return params + ("oauth_signature" to signature)
    }
}