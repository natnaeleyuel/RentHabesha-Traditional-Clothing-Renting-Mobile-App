package com.jct.renthabesha.core.data.remote.api

import com.jct.renthabesha.core.data.model.AuthLoginResponse
import com.jct.renthabesha.core.data.remote.api.AuthApiService.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    @POST("auth/register")
    suspend fun signUp(
        @Body request: AuthSignupRequest
    ): Response<AuthSignupResponse>

    @POST("auth/login")
    suspend fun signIn(
        @Body request: AuthLoginRequest
    ): Response<AuthLoginResponse>

    @POST("auth/logout")
    suspend fun logout(
        @Body request: LogoutRequest
    ): Response<AuthLogoutResponse>

    data class AuthLoginRequest(
        val email: String,
        val password: String
    )

    data class AuthSignupRequest(
        val name: String,
        val email: String,
        val password: String,
        val phone: String,
        val address: String,
        val gender: String
    )

    data class LogoutRequest(
        val token: String
    )

    data class AuthLogoutResponse(
        val success: Boolean,
    )

    data class User(
        val _id: String,
        val name: String,
        val email: String,
        val phone: String?,
        val address: String?,
        val gender: String?,
        val profilePhoto: String?
    )
}

data class AuthSignupResponse(
    val success: Boolean,
    val user: User,
    val message: String? = null
)