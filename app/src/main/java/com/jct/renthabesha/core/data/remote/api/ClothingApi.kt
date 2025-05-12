package com.jct.renthabesha.core.data.remote.api

import com.jct.renthabesha.core.data.remote.api.AuthApiService.User
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ClothingApiService {
    @Multipart
    @POST("clothing")
    suspend fun addClothingItem(
        @Part("title") title: RequestBody,
        @Part("description") description: RequestBody,
        @Part("pricePerDay") pricePerDay: RequestBody,
        @Part("type") type: RequestBody,  // "Men", "Women", "Kids"
        @Part("location") location: RequestBody,
        @Part images: MultipartBody.Part,
    ): Response<ClothingItemResponse>

    @Multipart
    @PUT("clothing/{id}")
    suspend fun updateClothingItem(
        @Path("id") id: String,
        @Part("title") title: RequestBody,
        @Part("description") description: RequestBody,
        @Part("pricePerDay") pricePerDay: RequestBody,
        @Part("type") type: RequestBody,
        @Part("location") location: RequestBody,
        @Part images: MultipartBody.Part?
    ): Response<ClothingItemResponse>

    @DELETE("clothing/{id}")
    suspend fun deleteClothingItem(
        @Path("id") id: String
    ): Response<DeleteResponse>

    @GET("clothing")
    suspend fun getAllClothingItems(): Response<List<ClothingItemResponse>>
}

data class ClothingItemResponse(
    val _id: String,
    val title: String,
    val description: String,
    val owner: User,
    val pricePerDay: Double,
    val images: List<String>,
    val type: String,
    val availability: Boolean,
    val location: String,
    val createdAt: String
)

data class DeleteResponse(
    val success: Boolean,
    val message: String
)



