package com.darashch.tasking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.darashch.tasking.ui.screen.tasklist.TaskListsScreen
import com.darashch.tasking.ui.theme.TaskingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskingTheme {
                TaskListsScreen()
            }
        }
    }
}
