package pt.ist.cmu.notesapp.data.model

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class Note(
    val id: Int,
    val title: String,
    val content: String,
    val timestamp: Date = Date()
) {
    // Format for displaying the date in a human-friendly way
    val formattedDate: String
        get() {
            val dateFormat = SimpleDateFormat("MMM dd, yyyy 'at' hh:mm a", Locale.getDefault())
            return dateFormat.format(timestamp)
        }
}