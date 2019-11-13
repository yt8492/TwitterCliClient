package json

import kotlinx.serialization.Serializable

@Serializable
data class TokenResponse(
    val tokenType: String,
    val accessToken: String
)