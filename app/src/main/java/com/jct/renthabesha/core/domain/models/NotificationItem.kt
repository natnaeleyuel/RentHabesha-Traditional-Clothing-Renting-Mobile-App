package com.jct.renthabesha.core.domain.models

import androidx.compose.ui.graphics.vector.ImageVector

data class NotificationItem(
    val id: Int,
    val title: String,
    val message: String,
    val icon: ImageVector? = null,
    val isNew: Boolean = true,
    val timestamp: String? = null
)