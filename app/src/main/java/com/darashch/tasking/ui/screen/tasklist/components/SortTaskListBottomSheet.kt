package com.darashch.tasking.ui.screen.tasklist.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.darashch.tasking.R


// NOTE: Temporary enum class for sort Options
enum class SortOption {
    ALPHABETICALLY_ASC,
    ALPHABETICALLY_DESC,
    CREATED_TIMESTAMP_ASC,
    CREATED_TIMESTAMP_DESC
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortTaskListBottomSheet(
    bottomSheetState: SheetState,
    selectedOption: SortOption,
    onDismissRequest: () -> Unit,
    onSelectedSortOption: (selectedOption: SortOption) -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = {
            onDismissRequest()
        },
        sheetState = bottomSheetState,
        modifier = Modifier.fillMaxHeight()
    ) {
        SortOption.entries.forEach { sortOption ->
            SortTaskListBottomSheetItem(
                sortOption = sortOption,
                isSelected = sortOption == selectedOption,
                onSelectedSortOption = { selectedOption ->
                    onSelectedSortOption(selectedOption)
                }
            )
        }
    }
}

@Composable
fun SortTaskListBottomSheetItem(
    sortOption: SortOption,
    isSelected: Boolean,
    onSelectedSortOption: (selectedOption: SortOption) -> Unit
) {
    val sortOptionText = when (sortOption) {
        SortOption.ALPHABETICALLY_ASC -> R.string.sort_alphabetically_az
        SortOption.ALPHABETICALLY_DESC -> R.string.sort_alphabetically_za
        SortOption.CREATED_TIMESTAMP_ASC -> R.string.sort_creation_asc
        SortOption.CREATED_TIMESTAMP_DESC -> R.string.sort_creation_desc
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onSelectedSortOption(sortOption)
            }
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 12.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(sortOptionText),
                fontSize = 16.sp
            )
            if (isSelected)
                Icon(
                    painter = painterResource(R.drawable.check),
                    contentDescription = null
                )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview (
    showSystemUi = true
)
@Composable
fun SortTaskListBottomSheetPreview() {
    SortTaskListBottomSheet(
        bottomSheetState = rememberModalBottomSheetState(),
        onDismissRequest = {},
        selectedOption = SortOption.ALPHABETICALLY_ASC,
        onSelectedSortOption = {}
    )
}