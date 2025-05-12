package com.jct.renthabesha.core.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jct.renthabesha.core.presentation.theme.Brand

@Composable
fun ButtonCompos(
    modifier: Modifier = Modifier,
    onClicked: () -> Unit,
    text: Int
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        onClick = {
            onClicked()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Brand,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(size = 40.dp)
    ) {
        Text(text = stringResource(id = text),
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium)
        )
    }
}