package com.darashch.tasking.ui.screen.tasklist.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.darashch.tasking.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListAppBar(
    onNewTaskClick: () -> Unit,
    onSortTaskListClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.app_name))
        },
        actions = {
            IconButton(
                onClick = {
                    onSortTaskListClick()
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.sort),
                    contentDescription = stringResource(R.string.sort_list)
                )
            }
            IconButton(
                onClick = {
                    onNewTaskClick()
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.add),
                    contentDescription = stringResource(R.string.new_task)
                )
            }
        }
    )
}