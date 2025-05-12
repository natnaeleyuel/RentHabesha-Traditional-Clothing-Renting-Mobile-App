package com.jct.renthabesha.core.domain.models

data class ClothingItem(
    var title: String,
    var description: String,
    var imageUrl: String,
    var price: Int,
    var rentalPeriod: String,
    var category: String,
    val dateAdded: Long,

    val id: Int = 0,
)