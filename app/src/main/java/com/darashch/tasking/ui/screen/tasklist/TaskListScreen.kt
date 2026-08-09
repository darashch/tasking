package com.darashch.tasking.ui.screen.tasklist



import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.darashch.tasking.R
import com.darashch.tasking.ui.screen.tasklist.components.NewTaskDialog
import com.darashch.tasking.ui.screen.tasklist.components.SortOption
import com.darashch.tasking.ui.screen.tasklist.components.SortTaskListBottomSheet
import com.darashch.tasking.ui.screen.tasklist.components.TaskListAppBar
import com.darashch.tasking.ui.screen.tasklist.components.TaskListItem
import com.darashch.tasking.ui.screen.tasklist.components.TaskListSearch
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen() {
    var showNewTaskDialog by remember { mutableStateOf(false) }
    val taskList = remember { mutableStateListOf<Pair<String, Boolean>>() } // NOTE: Temporary variable for task list
    val newTaskName = rememberTextFieldState() // NOTE: Temporary variable for task name

    // NOTE: Temporary variables for sorting selection
    var showSortTaskListSheet by remember { mutableStateOf(false) }
    var selectedSortOption by remember { mutableStateOf(SortOption.ALPHABETICALLY_ASC) }
    val sortTaskListBottomSheetState = rememberModalBottomSheetState()
    val sortTaskListBottomSheetScope = rememberCoroutineScope()


    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TaskListAppBar(
                onNewTaskClick = {
                    showNewTaskDialog = true
                },
                onSortTaskListClick = {
                    showSortTaskListSheet = true
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

            // "Sort Task List" Bottom Sheet
            if (showSortTaskListSheet)
                SortTaskListBottomSheet(
                    bottomSheetState = sortTaskListBottomSheetState,
                    selectedOption = selectedSortOption,
                    onDismissRequest = {
                        showSortTaskListSheet = false
                    },
                    onSelectedSortOption = { selectedOption ->
                        selectedSortOption = selectedOption

                        sortTaskListBottomSheetScope.launch {
                            sortTaskListBottomSheetState.hide()
                        }.invokeOnCompletion {
                            if (!sortTaskListBottomSheetState.isVisible) {
                                showSortTaskListSheet = false
                            }
                        }
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