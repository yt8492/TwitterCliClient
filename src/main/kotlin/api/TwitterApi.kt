package api

import api.json.TokenResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface TwitterApi {

    @FormUrlEncoded
    @POST("oauth2/token")
    suspend fun oauth2Token(
        @Field("grant_type")
        grantType: String
    ): TokenResponse
}