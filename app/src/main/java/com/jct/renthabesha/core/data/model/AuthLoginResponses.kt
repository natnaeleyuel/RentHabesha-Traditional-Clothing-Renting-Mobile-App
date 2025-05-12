package com.jct.renthabesha.core.data.model

import com.jct.renthabesha.core.data.remote.api.AuthApiService.User

data class AuthLoginResponse(
    val success: Boolean,
    val token: String,
    val refreshToken: String?,
    val user: User,
    val message: String? = null
)