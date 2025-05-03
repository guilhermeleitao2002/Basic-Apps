package com.example.simplecalculator.viewComponent

// Imports
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.graphics.Color
import com.example.simplecalculator.viewModelComponent.CalculatorViewModel

// View
@Composable
fun CalculatorScreen(viewModel: CalculatorViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Simple Calculator",
            style = MaterialTheme.typography.headlineLarge
        )

        // Value A input
        OutlinedTextField(
            value = viewModel.getValueA(),
            onValueChange = { viewModel.updateValueA(it) },
            label = { Text("Value A") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        // Value B input
        OutlinedTextField(
            value = viewModel.getValueB(),
            onValueChange = { viewModel.updateValueB(it) },
            label = { Text("Value B") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        // Operation buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OperationButton(text = "+", onClick = { viewModel.calculate("+") })
            OperationButton(text = "-", onClick = { viewModel.calculate("-") })
            OperationButton(text = "×", onClick = { viewModel.calculate("×") })
            OperationButton(text = "÷", onClick = { viewModel.calculate("÷") })
        }

        // Result display
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            shape = MaterialTheme.shapes.medium
        ) {
            Text(
                text = "Result: ${viewModel.getResult()}",
                modifier = Modifier.padding(16.dp)
            )
        }

        // Button to clear
        Button(
            onClick = { viewModel.reset() },
            shape = MaterialTheme.shapes.medium,
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                containerColor = Color.Red,
                contentColor = Color.White
            ),
            modifier = Modifier.align(Alignment.End)
        ) {

            Text(text = "Clear", style = MaterialTheme.typography.headlineSmall)
        }
    }
}

@Composable
fun OperationButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.size(60.dp)
    ) {
        Text(text = text, style = MaterialTheme.typography.headlineSmall)
    }
}