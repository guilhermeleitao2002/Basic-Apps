package pt.ist.cmu.notesapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import pt.ist.cmu.notesapp.data.model.Note
import pt.ist.cmu.notesapp.data.repository.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class NotesViewModel(
    private val repository: NoteRepository = NoteRepository()
) : ViewModel() {

    val notes: StateFlow<List<Note>> = repository.notes
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val _noteTitle = MutableStateFlow("")
    val noteTitle: StateFlow<String> = _noteTitle.asStateFlow()

    private val _noteContent = MutableStateFlow("")
    val noteContent: StateFlow<String> = _noteContent.asStateFlow()

    fun updateNoteTitle(title: String) {
        _noteTitle.update { title }
    }

    fun updateNoteContent(content: String) {
        _noteContent.update { content }
    }

    fun saveNote() {
        if (_noteTitle.value.isBlank() && _noteContent.value.isBlank()) {
            return
        }

        val newNote = Note(
            id = repository.getIdCounter(),
            title = _noteTitle.value,
            content = _noteContent.value
        )
        repository.incrementIdCounter()

        repository.addNote(newNote)

        // Clear the input fields
        _noteTitle.update { "" }
        _noteContent.update { "" }
    }

    fun deleteNote(noteId: Int) {
        repository.deleteNote(noteId)
    }

    fun getNoteById(noteId: Int): Note? {
        return repository.getNoteById(noteId)
    }
}