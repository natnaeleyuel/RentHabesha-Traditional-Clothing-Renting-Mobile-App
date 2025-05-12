package com.jct.renthabesha.core.presentation.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jct.renthabesha.core.presentation.theme.bodyText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationCompos(
    modifier: Modifier = Modifier,
    title: String,
    screen: @Composable (Modifier) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
        state = rememberTopAppBarState()
    )
    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .padding(50.dp),

        topBar = {
            TopBar(
                title = "Settings",
                scrollBehavior = scrollBehavior
            )
        },

        bottomBar = {
            BottomBar()
        }
    ) {
        paddingValues -> @androidx.compose.runtime.Composable {
            screen(Modifier.padding(paddingValues))
        }
    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    title: String,
    scrollBehavior: TopAppBarScrollBehavior
) {
    TopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.bodyText,
                style = MaterialTheme.typography.titleMedium
            )
        },

        windowInsets = WindowInsets(top = 0.dp),

        navigationIcon = {
            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = "Navigation Back Icon"
            )
        }

    )
}

@Composable
fun BottomBar(modifier: Modifier = Modifier) {
    NavigationBar(modifier = Modifier.padding(horizontal = 20.dp),
    ) {
        NavigationRailItem(
            selected = true,
            onClick = {},
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null
                )
            }
        )

        NavigationRailItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = null
                )
            }
        )

        NavigationRailItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null
                )
            }
        )
    }
}