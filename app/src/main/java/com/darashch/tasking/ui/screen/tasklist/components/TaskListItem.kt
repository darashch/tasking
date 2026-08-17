package com.darashch.tasking.ui.screen.tasklist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.darashch.tasking.R

@Composable
fun TaskListItem(
    taskName: String,
    isCompleted: Boolean,
    onCompleteTask: (String) -> Unit,
    onDeleteTask: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clip(shape = RoundedCornerShape(5.dp))
            .background(color = Color.White)
            .clickable {
                // TODO: Implement "Edit Task" Page
            }
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(vertical = 5.dp)
        ) {
            Checkbox(
                checked = isCompleted,
                onCheckedChange = {
                    onCompleteTask(taskName)
                    // TODO: Implement "Task Complete" functionality using Room Databse
                }
            )
            Text(
                modifier = Modifier
                    .weight(.7f),
                text = taskName,
                style = TextStyle(
                    textDecoration = if (isCompleted) TextDecoration.LineThrough else TextDecoration.None
                )
            )
            IconButton(
                onClick = {
                    onDeleteTask(taskName)
                    // TODO: Implement "Delete Task" functionality using Room database
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.delete),
                    contentDescription = stringResource(R.string.delete_task),
                    tint = Color.Red
                )
            }
        }
    }
}