package com.jct.renthabesha.core.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Brand = Color(0xFF19B579)
val StrokeStrong = Color(0xFF288964)
val StrokeWeak = Color(0xFF19B579)
val Background = Color(0xFFF6F7FA)
val TextStrong = Color(0xFF000000)
val TextWeak = Color(0xFF8086A6)
val AmberStatus = Color(0xFFFFA000)
val GreenStatus = Color(0xFF4CAF50)

val ColorScheme.focusedTextFieldText
    @Composable
    get() = if (isSystemInDarkTheme()) Color.White else TextStrong

val ColorScheme.unfocusedTextFieldText
    @Composable
    get() = if (isSystemInDarkTheme()) Color.Gray else TextWeak

val ColorScheme.focusedTextFieldStroke
    @Composable
    get() = if (isSystemInDarkTheme()) Color.White else StrokeStrong

val ColorScheme.unfocusedTextFieldStroke
    @Composable
    get() = if (isSystemInDarkTheme()) Color.Gray else StrokeWeak

val ColorScheme.bodyText
    @Composable
    get() = if (isSystemInDarkTheme()) Color.White else TextStrong