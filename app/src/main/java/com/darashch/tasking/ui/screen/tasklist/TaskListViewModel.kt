package com.darashch.tasking.ui.screen.tasklist


import androidx.lifecycle.ViewModel
import com.darashch.tasking.ui.screen.tasklist.components.SortOption
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TaskListViewModel: ViewModel() {
    private val _uiState: MutableStateFlow<TaskListUiState> = MutableStateFlow(TaskListUiState())
    val uiState: StateFlow<TaskListUiState> = _uiState.asStateFlow()

    fun openNewTaskDialog() {
        _uiState.update { newUiState ->
            newUiState.copy(
                showNewTaskDialog = true
            )
        }
    }

    fun closeNewTaskDialog() {
        _uiState.update { newUiState ->
            newUiState.copy(
                showNewTaskDialog = false
            )
        }
    }

    fun openSortTaskListSheet() {
        _uiState.update { newUiState ->
            newUiState.copy(
                showSortTaskListSheet = true
            )
        }
    }

    fun closeSortTaskListSheet() {
        _uiState.update { newUiState ->
            newUiState.copy(
                showSortTaskListSheet = false
            )
        }
    }

    fun onSelectedSortOption(selectedSortOption: SortOption) {
        _uiState.update { newUiState ->
            newUiState.copy(
                selectedSortOption = selectedSortOption
            )
        }
    }

    fun addNewTask(taskName: String) {
        _uiState.update { newUiState ->
            newUiState.copy(
                taskList = newUiState.taskList.plus(taskName to false)
            )
        }
    }

    fun onCompleteTask(taskToComplete: String) {
        _uiState.update { newUiState ->
            newUiState.copy(
                taskList = newUiState.taskList.map { task ->
                    if (task.first == taskToComplete) task.copy(second = !task.second) else task
                }
            )
        }
    }

    fun onDeleteTask(taskToDelete: String) {
        _uiState.update { newUiState ->
            newUiState.copy(
                taskList = newUiState.taskList.filter { task ->
                    task.first != taskToDelete
                }
            )
        }
    }
}