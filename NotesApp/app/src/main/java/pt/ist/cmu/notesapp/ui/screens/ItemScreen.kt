@file:OptIn(ExperimentalMaterial3Api::class)    // This is only needed for the @OptIn annotation of the TopAppBar

package pt.ist.cmu.notesapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import pt.ist.cmu.notesapp.viewmodel.NotesViewModel

@Composable
fun ItemScreen(
    noteId: Int,
    onBackClick: () -> Unit,
    viewModel: NotesViewModel = viewModel()
) {
    // Find the note with the matching ID
    val note = viewModel.getNoteById(noteId)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Note Details") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            if (note != null) {
                // Display note title
                Text(
                    text = note.title,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                    modifier = Modifier.padding(bottom = 50.dp)
                )

                // Display note content
                Text(
                    text = note.content,
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                // Additional timestamp section (type: timestamp)
                // Display it in a friendlier format than "Sun May 04 12:08:58 GMT+01:00 2025" -> "04/05/2025 12:08"
                // Put at the end of the screen
                Text(
                    text = "Created at: ${note.formattedDate}",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = MaterialTheme.typography.labelSmall.fontSize
                )
            } else {
                // Display error message if note is not found
                Text(
                    text = "Note not found!",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}