package com.jct.renthabesha.core.presentation.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.jct.renthabesha.core.domain.models.UserItem

class UsersViewModel : ViewModel() {
    private val _users = mutableStateListOf(
        UserItem(name = "User1", joinDate = "20-03-2025"),
        UserItem(name = "User2", joinDate = "10-02-2025"),
        UserItem(name = "User3", joinDate = "07-11-2024"),
        UserItem(name = "User4", joinDate = "28-10-2024"),
        UserItem(name = "User5", joinDate = "30-09-2024", lastActive = "Inactive 30-09-2024")
    )

    val users: List<UserItem> = _users

    fun toggleUserSelection(userId: String) {
        _users.replaceAll { user ->
            if (user.id == userId) user.copy(isSelected = !user.isSelected) else user
        }
    }

    fun selectAll(select: Boolean) {
        _users.replaceAll { it.copy(isSelected = select) }
    }
}