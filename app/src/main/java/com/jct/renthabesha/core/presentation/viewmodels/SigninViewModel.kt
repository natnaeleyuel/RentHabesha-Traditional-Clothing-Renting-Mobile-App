package com.jct.renthabesha.core.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jct.renthabesha.core.data.remote.api.AuthApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject

@HiltViewModel
class SigninViewModel @Inject constructor(
    private val authApi: AuthApiService,
) : ViewModel() {

    var state by mutableStateOf(SigninState())
        private set

    fun onEvent(event: SigninEvent) {
        when (event) {
            is SigninEvent.FieldUpdate -> {
                state = state.copy(fields = state.fields.copy(
                    email = event.email ?: state.fields.email,
                    password = event.password ?: state.fields.password,
                ))
            }
            is SigninEvent.Submit -> {
                if (state.fields.email.isBlank() || state.fields.password.isBlank()) {
                    state = state.copy(error = "Email and password are required")
                } else {
                    signIn()
                }
            }
        }
    }

    private fun signIn() {
        viewModelScope.launch {
            state = state.copy(isLoading = true, error = null)
            try {
                val response = authApi.signIn(
                    AuthApiService.AuthLoginRequest(
                        email = state.fields.email,
                        password = state.fields.password
                    )
                )

                if (response.isSuccessful) {
                    state = state.copy(isSuccess = true)
                } else {
                    state = state.copy(error = response.message() ?: "Unknown error")
                }
            } catch (e: Exception) {
                state = state.copy(error = e.message ?: "Network error")
            } finally {
                state = state.copy(isLoading = false)
            }
        }
    }
}

data class SigninState(
    val fields: SigninFields = SigninFields(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false
)

data class SigninFields(
    val email: String = "",
    val password: String = "",
)

sealed class SigninEvent {
    data class FieldUpdate(
        val email: String? = null,
        val password: String? = null,
    ) : SigninEvent()

    object Submit : SigninEvent()
}






