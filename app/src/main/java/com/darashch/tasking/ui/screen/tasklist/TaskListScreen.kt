package com.darashch.tasking.ui.screen.tasklist



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.darashch.tasking.R
import com.darashch.tasking.ui.screen.tasklist.components.NewTaskDialog
import com.darashch.tasking.ui.screen.tasklist.components.TaskListAppBar
import com.darashch.tasking.ui.screen.tasklist.components.TaskListItem
import com.darashch.tasking.ui.screen.tasklist.components.TaskListSearch


@Composable
fun TaskListScreen() {
    var showNewTaskDialog by remember { mutableStateOf(false) }
    val taskList = remember { mutableStateListOf<Pair<String, Boolean>>() } // NOTE: Temporary variable for task list
    val newTaskName = rememberTextFieldState() // NOTE: Temporary variable for task name

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TaskListAppBar(
                onNewTaskClick = {
                    showNewTaskDialog = true
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 18.dp)
        ) {
            TaskListSearch()

            if (taskList.isEmpty())
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = stringResource(R.string.empty_task_list))
                }
            else
                Column(
                    modifier = Modifier
                        .verticalScroll(state = rememberScrollState())
                ) {
                    taskList.forEach { task ->
                        TaskListItem(
                            taskName = task.first,
                            isCompleted = task.second
                        )
                    }
                }

            // "New Task" Dialog
            if (showNewTaskDialog)
                NewTaskDialog(
                    newTaskState = newTaskName,
                    onDismissRequest = {
                        showNewTaskDialog = false
                    },
                    onCreateTask = {
                        taskList.add(newTaskName.text.toString() to false)
                        showNewTaskDialog = false

                        // TODO: Implement "New Task" using Room Database
                    },
                    onCancelTaskCreation = {
                        showNewTaskDialog = false
                    }
                )
        }
    }
}

@Preview (
    showSystemUi = true
)
@Composable
fun TaskListScreenPreview() {
    TaskListScreen()
}