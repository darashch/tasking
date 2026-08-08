package com.darashch.tasking.ui.screen.tasklist.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.darashch.tasking.R

@Composable
fun TaskListSearch() {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 8.dp
            ),
        state = rememberTextFieldState(),
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.search),
                contentDescription = stringResource(R.string.search_task)
            )
        },
        placeholder = {
            Text(stringResource(R.string.search_task))
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent
        ),
        lineLimits = TextFieldLineLimits.SingleLine,
        shape = RoundedCornerShape(size = 5.dp)
    )
}