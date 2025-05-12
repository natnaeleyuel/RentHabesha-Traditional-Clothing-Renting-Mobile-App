package com.jct.renthabesha.core.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.jct.renthabesha.core.presentation.components.ManageUsersCard
import com.jct.renthabesha.core.presentation.viewmodels.UsersViewModel

@Composable
fun ProductDescription(
    description: String,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val displayText = if (expanded) description else description.take(50) + "..."

    Column(modifier = modifier) {
        Text(
            text = "Product Details",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )

        Text(
            text = displayText,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 4.dp)
        )

        TextButton(
            onClick = { expanded = !expanded },
            modifier = Modifier.padding(top = 4.dp)
        ) {
            Text(text = if (expanded) "Read less" else "Read more")
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = if (expanded) "Collapse" else "Expand",
                modifier = Modifier.rotate(if (expanded) 180f else 0f)
            )
        }
    }
}