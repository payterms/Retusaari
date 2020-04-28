package ru.payts.retusaari.ui.note

import androidx.lifecycle.ViewModel
import ru.payts.retusaari.data.NotesRepository
import ru.payts.retusaari.data.entity.Note

class NoteViewModel : ViewModel() {

    private var pendingNote: Note? = null

    fun save(note: Note) {
        pendingNote = note
    }

    override fun onCleared() {
        pendingNote?.let {
            NotesRepository.saveNote(it)
        }
    }
}