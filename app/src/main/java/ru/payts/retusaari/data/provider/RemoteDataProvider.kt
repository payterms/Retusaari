package ru.payts.retusaari.data.provider

import kotlinx.coroutines.channels.ReceiveChannel
import ru.payts.retusaari.data.entity.Note
import ru.payts.retusaari.data.entity.User
import ru.payts.retusaari.data.model.NoteResult

interface RemoteDataProvider {
    fun subscribeToAllNotes(): ReceiveChannel<NoteResult>
    suspend fun getNoteById(id: String): Note
    suspend fun saveNote(note: Note): Note
    suspend fun getCurrentUser(): User?
    suspend fun deleteNote(noteId: String)
}