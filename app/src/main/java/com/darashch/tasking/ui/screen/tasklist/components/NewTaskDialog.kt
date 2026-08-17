package com.darashch.tasking.ui.screen.tasklist.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.darashch.tasking.R
import kotlinx.coroutines.android.awaitFrame

@Composable
fun NewTaskDialog(
    newTaskState: TextFieldState,
    onDismissRequest: () -> Unit,
    onCreateTask: () -> Unit,
    onCancelTaskCreation: () -> Unit
) {
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(focusRequester) {
        awaitFrame()
        focusRequester.requestFocus()
    }

    Dialog(
        onDismissRequest = {
            newTaskState.clearText()
            onDismissRequest()
        }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(size = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding( 16.dp),
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = stringResource(R.string.create_new_task),
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester),
                    state = newTaskState,
                    placeholder = {
                        Text(text = stringResource(R.string.enter_task_name))
                    },
                    lineLimits = TextFieldLineLimits.SingleLine
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        onClick = {
                            newTaskState.clearText()
                            onCancelTaskCreation()
                        }
                    ) {
                        Text(text = stringResource(R.string.btn_cancel_create_task))
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    TextButton(
                        onClick = {
                            onCreateTask()
                            newTaskState.clearText()
                        }
                    ) {
                        Text(text = stringResource(R.string.btn_create_task))
                    }
                }
            }
        }
    }
}


@Preview (
    showSystemUi = true
)
@Composable
fun NewTaskDialogPreview() {
    NewTaskDialog(
        newTaskState = rememberTextFieldState(),
        onDismissRequest = {},
        onCreateTask = {},
        onCancelTaskCreation = {}
    )
}