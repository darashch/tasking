package com.darashch.tasking.ui.screen.tasklist



import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.darashch.tasking.R


@Composable
fun TaskListsScreen() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar()
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 18.dp)
        ) {
            SearchTaskTextField()
            Column(
                modifier = Modifier
                    .verticalScroll(state = rememberScrollState())
            ) {
                TaskListItem(
                    taskName = "Task 1",
                    isCompleted = true
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar() {
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.app_name))
        },
        actions = {
            IconButton(
                onClick = {
                    // TODO: Implement "Sort Task List" functionality
                }
            ) {
                Icon(
                    painter = painterResource(R.drawable.sort),
                    contentDescription = stringResource(R.string.sort_list)
                )
            }
            IconButton(
                onClick = {
                    // TODO: Implement "Add Task"
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

@Composable
fun SearchTaskTextField() {
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

@Composable
fun TaskListItem(
    taskName: String,
    isCompleted: Boolean
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
                    // TODO: Implement "Task Complete" functionality
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
                    // TODO: Implement "Delete Task" functionality
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

@Preview (
    showSystemUi = true
)
@Composable
fun TaskListsScreenPreview() {
    TaskListsScreen()
}