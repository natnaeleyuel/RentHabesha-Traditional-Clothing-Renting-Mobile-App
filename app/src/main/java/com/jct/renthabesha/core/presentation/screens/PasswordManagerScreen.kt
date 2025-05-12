package com.jct.renthabesha.core.presentation.screens

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jct.renthabesha.R
import com.jct.renthabesha.core.presentation.components.ButtonCompos
import com.jct.renthabesha.core.presentation.components.TextField
import com.jct.renthabesha.core.presentation.components.TopNavigationBar
import com.jct.renthabesha.core.presentation.navigation.Screen
import com.jct.renthabesha.core.presentation.theme.Brand
import com.jct.renthabesha.core.presentation.theme.Poppins

@Composable
fun PasswordManagerScreen(
    navController: NavController,
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp, vertical = 60.dp),
        ) {
            TopNavigationBar(title = "Password Manager", onClick = { navController.navigate(Screen.Settings.route) })
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(
                    modifier = Modifier,
                    onClick = { /* Handle back navigation */ },
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_left_icon),
                        contentDescription = "Go Back",
                        tint = if (isSystemInDarkTheme()) Color.White else Color.Black
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    modifier = Modifier,
                    text = stringResource(id = R.string.pw_mang),
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Medium),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = stringResource(id = R.string.curr_pass),
                    placeholder = stringResource(id = R.string.password),
                    onValueChanged = TODO(),
                    value = TODO()
                );

                Spacer(modifier = Modifier.height(5.dp));

                TextButton(
                    modifier = Modifier.align(Alignment.End),
                    onClick = { }
                ) {
                    Text(
                        text = "Forgot Password?",
                        style = TextStyle(
                            fontFamily = Poppins,
                            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                            color = Brand,
                            textDecoration = TextDecoration.Underline
                        )
                    )
                }

                Spacer(modifier = Modifier.height(25.dp));

                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = stringResource(id = R.string.new_pass),
                    placeholder = stringResource(id = R.string.password),
                    onValueChanged = TODO(),
                    value = TODO()
                );

                Spacer(modifier = Modifier.height(25.dp));

                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = stringResource(id = R.string.conf_pass),
                    placeholder = stringResource(id = R.string.password),
                    onValueChanged = TODO(),
                    value = TODO()
                );

                Spacer(modifier = Modifier.height(40.dp));

                ButtonCompos(
                    modifier = Modifier,
                    onClicked = {},
                    R.string.change_pass
                )
            }
        }
    }
}