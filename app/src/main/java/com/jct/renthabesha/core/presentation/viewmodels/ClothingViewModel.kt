package com.jct.renthabesha.core.presentation.viewmodels

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jct.renthabesha.core.data.remote.api.ClothingApiService
import com.jct.renthabesha.core.data.remote.api.ClothingItemResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

class ClothingViewModel : ViewModel() {
    private val _clothingItems = MutableStateFlow<List<ClothingItemResponse>>(emptyList())
    val clothingItems: StateFlow<List<ClothingItemResponse>> = _clothingItems

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val apiService: ClothingApiService by lazy {
        Retrofit.Builder()
            .baseUrl("YOUR_BACKEND_BASE_URL") // Replace with your actual backend URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ClothingApiService::class.java)
    }

    init {
        fetchClothingItems()
    }

    fun fetchClothingItems() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val response = apiService.getAllClothingItems()
                if (response.isSuccessful) {
                    _clothingItems.value = response.body() ?: emptyList()
                } else {
                    _errorMessage.value = "Failed to load items: ${response.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Network error: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addClothingItem(
        title: String,
        description: String,
        pricePerDay: Double,
        type: String,
        location: String,
        imageUri: Uri?
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                // Convert image to MultipartBody.Part
                val imagePart = imageUri?.let { uri ->
                    val file = File(uri.path!!)
                    val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
                    MultipartBody.Part.createFormData("images", file.name, requestFile)
                }

                val response = apiService.addClothingItem(
                    title = title.toRequestBody("text/plain".toMediaTypeOrNull()),
                    description = description.toRequestBody("text/plain".toMediaTypeOrNull()),
                    pricePerDay = pricePerDay.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
                    type = type.toRequestBody("text/plain".toMediaTypeOrNull()),
                    location = location.toRequestBody("text/plain".toMediaTypeOrNull()),
                    images = imagePart ?: throw IllegalArgumentException("Image is required")
                )

                if (response.isSuccessful) {
                    response.body()?.let { newItem ->
                        _clothingItems.value = _clothingItems.value + newItem
                    }
                } else {
                    _errorMessage.value = "Failed to add item: ${response.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateClothingItem(
        id: String,
        title: String,
        description: String,
        pricePerDay: Double,
        type: String,
        location: String,
        imageUri: Uri?
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                // Convert image to MultipartBody.Part if provided
                val imagePart = imageUri?.let { uri ->
                    val file = File(uri.path!!)
                    val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
                    MultipartBody.Part.createFormData("images", file.name, requestFile)
                }

                val response = apiService.updateClothingItem(
                    id = id,
                    title = title.toRequestBody("text/plain".toMediaTypeOrNull()),
                    description = description.toRequestBody("text/plain".toMediaTypeOrNull()),
                    pricePerDay = pricePerDay.toString().toRequestBody("text/plain".toMediaTypeOrNull()),
                    type = type.toRequestBody("text/plain".toMediaTypeOrNull()),
                    location = location.toRequestBody("text/plain".toMediaTypeOrNull()),
                    images = imagePart
                )

                if (response.isSuccessful) {
                    response.body()?.let { updatedItem ->
                        _clothingItems.value = _clothingItems.value.map {
                            if (it._id == updatedItem._id) updatedItem else it
                        }
                    }
                } else {
                    _errorMessage.value = "Failed to update item: ${response.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun deleteClothingItem(id: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val response = apiService.deleteClothingItem(id)
                if (response.isSuccessful && response.body()?.success == true) {
                    _clothingItems.value = _clothingItems.value.filter { it._id != id }
                } else {
                    _errorMessage.value = "Failed to delete item: ${response.body()?.message ?: response.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}