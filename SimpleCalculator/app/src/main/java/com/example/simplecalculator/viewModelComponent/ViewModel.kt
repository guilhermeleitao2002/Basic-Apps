package com.example.simplecalculator.viewModelComponent

// Imports
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

// Model + ViewModel
class CalculatorViewModel : ViewModel() {
    // States for input values and result
    private val _valueA = mutableStateOf("")

    private val _valueB = mutableStateOf("")

    private val _result = mutableStateOf("")

    // Functions to update values
    fun updateValueA(value: String) {
        _valueA.value = value
    }

    fun updateValueB(value: String) {
        _valueB.value = value
    }

    // Getters
    fun getValueA() : String {
        return _valueA.value
    }

    fun getValueB() : String {
        return _valueB.value
    }

    fun getResult() : String {
        return _result.value
    }

    // Function to perform calculation based on operation
    fun calculate(operation: String) {
        // Validate inputs
        val numA = _valueA.value.toDoubleOrNull()
        val numB = _valueB.value.toDoubleOrNull()

        if (numA == null || numB == null) {
            _result.value = "Invalid input"
            return
        }

        _result.value = when (operation) {
            "+" -> (numA + numB).toString()
            "-" -> (numA - numB).toString()
            "×" -> (numA * numB).toString()
            "÷" -> {
                if (numB == 0.0) {
                    "Cannot divide by zero"
                } else {
                    (numA / numB).toString()
                }
            }
            else -> "Invalid operation"
        }
    }

    // Reset function
    fun reset() {
        _valueA.value = ""
        _valueB.value = ""
        _result.value = ""
    }
}