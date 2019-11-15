package api.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokenResponse(
    @SerialName("token_type")
    val tokenType: String,
    @SerialName("access_token")
    val accessToken: String
)