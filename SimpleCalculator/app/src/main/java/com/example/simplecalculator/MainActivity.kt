package com.example.simplecalculator

// Imports
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Main Activity
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.padding(50.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        var a by remember { mutableStateOf("") }
                        var b by remember { mutableStateOf("") }
                        var result by remember { mutableStateOf("") }

                        Text(
                            text = "Simple Calculator",
                            fontSize = 24.sp,
                            modifier = Modifier.padding(bottom = 24.dp)
                        )

                        TextField(
                            value = a,
                            onValueChange = { a = it },
                            label = { Text("Value A") },
                            modifier = Modifier.padding(bottom = 8.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )

                        TextField(
                            value = b,
                            onValueChange = { b = it },
                            label = { Text("Value B") },
                            modifier = Modifier.padding(bottom = 16.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )

                        Row {
                            Button(
                                onClick = {
                                    val numA = a.toDoubleOrNull() ?: 0.0
                                    val numB = b.toDoubleOrNull() ?: 0.0
                                    result = (numA + numB).toString()
                                }
                            ) {
                                Text("+")
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Button(
                                onClick = {
                                    val numA = a.toDoubleOrNull() ?: 0.0
                                    val numB = b.toDoubleOrNull() ?: 0.0
                                    result = (numA - numB).toString()
                                }
                            ) {
                                Text("-")
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Button(
                                onClick = {
                                    val numA = a.toDoubleOrNull() ?: 0.0
                                    val numB = b.toDoubleOrNull() ?: 0.0
                                    result = (numA * numB).toString()
                                }
                            ) {
                                Text("×")
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Button(
                                onClick = {
                                    val numA = a.toDoubleOrNull() ?: 0.0
                                    val numB = b.toDoubleOrNull() ?: 0.0
                                    result = if (numB != 0.0) {
                                        (numA / numB).toString()
                                    } else {
                                        "Error: Divide by zero"
                                    }
                                }
                            ) {
                                Text("÷")
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        Text(
                            text = "Result: $result",
                            fontSize = 18.sp
                        )

                        // Button to clear
                        Button(
                            onClick = {
                                a = ""
                                b = ""
                                result = ""
                            },
                            shape = MaterialTheme.shapes.medium,
                            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                                containerColor = Color.Red,
                                contentColor = Color.White
                            ),
                            // At the last row and last column
                            modifier = Modifier.align(Alignment.End)
                        ) {
                            Text(text = "Clear", style = MaterialTheme.typography.headlineSmall)
                        }
                    }
                }
            }
        }
    }
}