package pt.ist.cmu.notesapp.data.repository

import pt.ist.cmu.notesapp.data.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NoteRepository {
    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    private val _idCounter = MutableStateFlow(0)
    val notes: Flow<List<Note>> = _notes.asStateFlow()

    fun addNote(note: Note) {
        _notes.update { currentNotes ->
            currentNotes + note
        }
    }

    fun deleteNote(noteId: Int) {
        _notes.update { currentNotes ->
            currentNotes.filter { it.id != noteId }
        }
    }

    fun getNoteById(noteId: Int): Note? {
        return _notes.value.find { it.id == noteId }
    }

    fun incrementIdCounter() {
        _idCounter.update { it + 1 }
    }

    fun getIdCounter(): Int {
        return _idCounter.value
    }

}