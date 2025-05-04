package pt.ist.cmu.notesapp.data.repository

import pt.ist.cmu.notesapp.data.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NoteRepository {
    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: Flow<List<Note>> = _notes.asStateFlow()

    fun addNote(note: Note) {
        _notes.update { currentNotes ->
            currentNotes + note
        }
    }

    fun deleteNote(noteId: String) {
        _notes.update { currentNotes ->
            currentNotes.filter { it.id != noteId }
        }
    }
}