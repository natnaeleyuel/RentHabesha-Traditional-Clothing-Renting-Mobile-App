package com.jct.renthabesha.core.data.remote.api

/*
// PaymentApiService.kt
interface PaymentApiService {
    @POST("payments/checkout")
    suspend fun checkout(
        @Header("Authorization") token: String,
        @Body payment: PaymentMethod
    ): Response<CheckoutResponse>
}

data class PaymentMethod(
    val cardNumber: String,
    val expiry: String, // "MM/YY"
    val cvc: String,
    val cardholderName: String
)

data class CheckoutResponse(
    val success: Boolean,
    val transactionId: String?,
    val bookingIds: List<String>?,
    val message: String?
)

// Usage
val payment = PaymentMethod(
    cardNumber = "4242424242424242",
    expiry = "12/25",
    cvc = "123",
    cardholderName = "John Doe"
)

val response = RetrofitClient.paymentService.checkout("Bearer $token", payment)
if (response.isSuccessful && response.body()?.success == true) {
    // Show success screen
    val transactionId = response.body()?.transactionId
} else {
    // Show error
    val error = response.body()?.message ?: "Checkout failed"
}

 */