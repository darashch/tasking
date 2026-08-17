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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.darashch.tasking.R
import com.darashch.tasking.ui.screen.tasklist.components.NewTaskDialog
import com.darashch.tasking.ui.screen.tasklist.components.SortTaskListBottomSheet
import com.darashch.tasking.ui.screen.tasklist.components.TaskListAppBar
import com.darashch.tasking.ui.screen.tasklist.components.TaskListItem
import com.darashch.tasking.ui.screen.tasklist.components.TaskListSearch
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(
    taskListViewModel: TaskListViewModel = viewModel()
) {
    val newTaskName = rememberTextFieldState()
    val sortTaskListBottomSheetState = rememberModalBottomSheetState()
    val sortTaskListBottomSheetScope = rememberCoroutineScope()

    val uiState by taskListViewModel.uiState.collectAsStateWithLifecycle()


    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TaskListAppBar(
                onNewTaskClick = {
                    taskListViewModel.openNewTaskDialog()
                },
                onSortTaskListClick = {
                    taskListViewModel.openSortTaskListSheet()
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

            if (uiState.taskList.isEmpty())
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
                    uiState.taskList.forEach { task ->
                        TaskListItem(
                            taskName = task.first,
                            isCompleted = task.second,
                            onCompleteTask = {
                                taskListViewModel.onCompleteTask(task.first)
                            },
                            onDeleteTask = {
                                taskListViewModel.onDeleteTask(task.first)
                            }
                        )
                    }
                }

            // "New Task" Dialog
            if (uiState.showNewTaskDialog)
                NewTaskDialog(
                    newTaskState = newTaskName,
                    onDismissRequest = {
                        taskListViewModel.closeNewTaskDialog()
                    },
                    onCreateTask = {
                        taskListViewModel.addNewTask(newTaskName.text.toString())
                        taskListViewModel.closeNewTaskDialog()

                        // TODO: Implement "New Task" using Room Database
                    },
                    onCancelTaskCreation = {
                        taskListViewModel.closeNewTaskDialog()
                    }
                )

            // "Sort Task List" Bottom Sheet
            if (uiState.showSortTaskListSheet)
                SortTaskListBottomSheet(
                    bottomSheetState = sortTaskListBottomSheetState,
                    selectedOption = uiState.selectedSortOption,
                    onDismissRequest = {
                        taskListViewModel.closeSortTaskListSheet()
                    },
                    onSelectedSortOption = { selectedOption ->
                        taskListViewModel.onSelectedSortOption(selectedOption)

                        sortTaskListBottomSheetScope.launch {
                            sortTaskListBottomSheetState.hide()
                        }.invokeOnCompletion {
                            if (!sortTaskListBottomSheetState.isVisible) {
                                taskListViewModel.closeSortTaskListSheet()
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