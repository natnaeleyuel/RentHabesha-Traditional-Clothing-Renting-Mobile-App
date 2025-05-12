package com.jct.renthabesha.core.domain.models

import java.util.UUID

data class UserItem(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val email: String? = null,
    val role: String? = null,
    val joinDate: String,
    val isSelected: Boolean = false,
    val lastActive: String? = null
)
