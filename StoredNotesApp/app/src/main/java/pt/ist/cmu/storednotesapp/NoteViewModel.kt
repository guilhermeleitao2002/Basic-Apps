package pt.ist.cmu.storednotesapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: NoteRepository
    val allNotes: StateFlow<List<Note>>

    private val _noteTitle = MutableStateFlow("")
    val noteTitle: StateFlow<String> = _noteTitle

    private val _noteContent = MutableStateFlow("")
    val noteContent: StateFlow<String> = _noteContent

    init {
        val noteDao = NoteDatabase.getDatabase(application).noteDao()
        repository = NoteRepository(noteDao)
        allNotes = repository.allNotes.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )
    }

    fun updateTitle(title: String) {
        _noteTitle.value = title
    }

    fun updateContent(content: String) {
        _noteContent.value = content
    }

    fun saveNote() {
        val title = noteTitle.value
        val content = noteContent.value

        if (title.isNotBlank() || content.isNotBlank()) {
            viewModelScope.launch {
                repository.insert(Note(title = title, content = content))
                _noteTitle.value = ""
                _noteContent.value = ""
            }
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            repository.delete(note)
        }
    }
}
