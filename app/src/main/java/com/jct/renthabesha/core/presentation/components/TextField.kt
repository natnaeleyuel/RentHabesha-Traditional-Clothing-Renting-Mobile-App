package com.jct.renthabesha.core.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.jct.renthabesha.R
import com.jct.renthabesha.R.drawable
import com.jct.renthabesha.core.presentation.theme.Brand
import com.jct.renthabesha.core.presentation.theme.focusedTextFieldStroke
import com.jct.renthabesha.core.presentation.theme.focusedTextFieldText
import com.jct.renthabesha.core.presentation.theme.unfocusedTextFieldStroke
import com.jct.renthabesha.core.presentation.theme.unfocusedTextFieldText

@Composable
fun TextField(
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String,
    placeholder: String,
) {

    var isShowPassword by remember {
        mutableStateOf(false)
    }
    
    if (label != "Password"){
        isShowPassword = true 
    }

    val focusRequester = remember {
        FocusRequester()
    };

    Column{
        Text(
            modifier = Modifier.clickable {
                focusRequester.requestFocus()
            },
            text = label,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Normal
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            modifier = modifier.focusRequester(focusRequester),
            value = value,
            onValueChange = onValueChanged,
            placeholder = {
                Text(text = placeholder)
            },

            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = MaterialTheme.colorScheme.focusedTextFieldStroke,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.unfocusedTextFieldStroke,
                focusedTextColor = MaterialTheme.colorScheme.focusedTextFieldText,
                unfocusedTextColor = MaterialTheme.colorScheme.unfocusedTextFieldText,
                focusedContainerColor = White,
                unfocusedContainerColor = White
            ),


            shape = RoundedCornerShape(20.dp),

            trailingIcon = {
                if ("Password" in label) {
                    Image(
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp)
                            .clickable { isShowPassword = !isShowPassword }
                            .align(Alignment.CenterHorizontally)
                            .clip(shape = RoundedCornerShape(15.dp)),
                        painter = painterResource(id = drawable.visibility_icon),
                        contentDescription = null,
                    )
                }else null
            },

            singleLine = true,

            visualTransformation = if (isShowPassword) VisualTransformation.None else PasswordVisualTransformation(),
        )
    }
}












