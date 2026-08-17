package com.darashch.tasking.ui.screen.tasklist


import com.darashch.tasking.ui.screen.tasklist.components.SortOption

data class TaskListUiState(
    val taskList: List<Pair<String, Boolean>> = emptyList(),
    val showNewTaskDialog: Boolean = false,
    val showSortTaskListSheet: Boolean = false,
    val selectedSortOption: SortOption = SortOption.ALPHABETICALLY_ASC
)