package com.jct.renthabesha.core.presentation.viewmodels

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jct.renthabesha.core.data.remote.api.AuthApiService
import com.jct.renthabesha.core.data.remote.api.AuthSignupResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val authApi: AuthApiService,
) : ViewModel() {

    var state by mutableStateOf(SignupState())
        private set

    val name: String get() = state.fields.name
    val email: String get() = state.fields.email
    val password: String get() = state.fields.password
    val phone: String get() = state.fields.phone
    val gender: String get() = state.fields.gender
    val address: String get() = state.fields.address

    fun onEvent(event: SignupEvent) {
        when (event) {
            is SignupEvent.FieldUpdate -> {
                state = state.copy(
                    fields = state.fields.copy(
                        name = event.name ?: state.fields.name,
                        email = event.email ?: state.fields.email,
                        password = event.password ?: state.fields.password,
                        phone = event.phone ?: state.fields.phone,
                        gender = event.gender ?: state.fields.gender,
                        address = event.address ?: state.fields.address,
                    )
                )
            }
            is SignupEvent.ProfileImageUpdate -> {
                state = state.copy(profileImageUri = event.uri)
            }
            is SignupEvent.Submit -> {
                if (validateStep3()) {
                    submitSignup()
                }
            }
            is SignupEvent.SetError -> {
                state = state.copy(error = event.message)
            }
        }
    }

    fun validateStep1(): Boolean {
        return name.isNotBlank() &&
                email.isNotBlank() && email.contains("@") &&
                password.length >= 6
    }

    fun validateStep2(): Boolean {
        return phone.isNotBlank() && gender.isNotBlank()
    }

    fun validateStep3(): Boolean {
        return address.isNotBlank()
    }

    private fun submitSignup() {
        viewModelScope.launch {
            state = state.copy(isLoading = true, error = null)
            try {
                val response = authApi.signUp(
                    AuthApiService.AuthSignupRequest(
                        name = name,
                        email = email,
                        password = password,
                        phone = "+251$phone",
                        address = address,
                        gender = gender
                    )
                )
                if (response.isSuccessful) {
                    state = state.copy(isSuccess = true)
                } else {
                    state = state.copy(error = response.message() ?: "Signup failed")
                }
            } catch (e: Exception) {
                state = state.copy(error = e.message ?: "Network error")
            } finally {
                state = state.copy(isLoading = false)
            }
        }
    }
}

data class SignupState(
    val fields: SignupFields = SignupFields(),
    val profileImageUri: Uri? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false
)

data class SignupFields(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val phone: String = "",
    val gender: String = "",
    val address: String = ""
)

sealed class SignupEvent {
    data class FieldUpdate(
        val name: String? = null,
        val email: String? = null,
        val password: String? = null,
        val phone: String? = null,
        val gender: String? = null,
        val address: String? = null,
    ) : SignupEvent()

    data class ProfileImageUpdate(val uri: Uri?) : SignupEvent()
    data class SetError(val message: String) : SignupEvent()
    object Submit : SignupEvent()
}