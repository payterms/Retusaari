package ru.payts.retusaari.data.provider

import androidx.lifecycle.LiveData
import ru.payts.retusaari.data.entity.Note
import ru.payts.retusaari.data.entity.User
import ru.payts.retusaari.data.model.NoteResult

interface RemoteDataProvider {
    fun subscribeToAllNotes(): LiveData<NoteResult>
    fun getNoteById(id: String): LiveData<NoteResult>
    fun saveNote(note: Note): LiveData<NoteResult>
    fun getCurrentUser(): LiveData<User?>
    fun deleteNote(noteId: String): LiveData<NoteResult>
}