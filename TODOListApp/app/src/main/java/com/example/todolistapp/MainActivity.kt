package com.example.todolistapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import com.example.todolistapp.ui.theme.TODOListAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TODOListAppTheme {
                val taskList = remember { mutableStateListOf<Task>() }
                // Keep track of removed tasks for the undo feature
                val removedTasks = remember { mutableStateListOf<Task>() }
                var text by remember { mutableStateOf("") }

                Column(modifier = Modifier.padding(16.dp)) {
                    OutlinedTextField(
                        value = text,
                        onValueChange = { text = it },
                        label = { Text("New task") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = {
                        if (text.isBlank()) {
                            // Make a pop-up or toast to tell the user to enter a task
                            Toast.makeText(
                                this@MainActivity,
                                "Please enter a task",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else if (taskList.any { it.title == text }) {
                            // Make a pop-up or toast to tell the user that the task already exists
                            Toast.makeText(
                                this@MainActivity,
                                "Task already exists",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            val task = Task(text)
                            taskList.add(task)
                            text = ""
                        }
                    }) {
                        Text("Add Task")
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Add Row for Clear All and Undo buttons
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(
                            onClick = {
                                // Store current list for potential undo
                                removedTasks.clear()
                                removedTasks.addAll(taskList)

                                // Clear all tasks
                                taskList.clear()

                                Toast.makeText(
                                    this@MainActivity,
                                    "All tasks cleared",
                                    Toast.LENGTH_SHORT
                                ).show()
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Clear All")
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Button(
                            onClick = {
                                if (removedTasks.isNotEmpty()) {
                                    // Restore tasks from the removed list
                                    taskList.clear()
                                    taskList.addAll(removedTasks)
                                    removedTasks.clear()

                                    Toast.makeText(
                                        this@MainActivity,
                                        "Tasks restored",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    Toast.makeText(
                                        this@MainActivity,
                                        "Nothing to undo",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            },
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Undo")
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    LazyColumn {
                        items(taskList) { task ->
                            androidx.compose.foundation.layout.Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                            ) {
                                androidx.compose.material3.Checkbox(
                                    checked = task.isDone,
                                    onCheckedChange = { isChecked ->
                                        // Update the task's isDone status
                                        val index = taskList.indexOf(task)
                                        if (index != -1) {
                                            taskList[index] = task.copy(isDone = isChecked)
                                        }
                                    }
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                Text(
                                    text = task.title,
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(vertical = 12.dp)
                                        .clickable {
                                            // Store the removed task for potential undo
                                            removedTasks.clear()
                                            removedTasks.add(task)

                                            // Remove the task when clicked
                                            taskList.remove(task)
                                            Toast.makeText(
                                                this@MainActivity,
                                                "Task removed",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        },
                                    color = if (task.isDone) Color.Green else Color.Red
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}