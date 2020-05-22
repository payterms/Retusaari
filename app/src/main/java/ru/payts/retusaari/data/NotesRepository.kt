package ru.payts.retusaari.data

import ru.payts.retusaari.data.entity.Note
import ru.payts.retusaari.data.provider.RemoteDataProvider

class NotesRepository(val remoteProvider: RemoteDataProvider) {

    fun getNotes() = remoteProvider.subscribeToAllNotes()
    suspend fun saveNote(note: Note) = remoteProvider.saveNote(note)
    suspend fun getNoteById(id: String) = remoteProvider.getNoteById(id)
    suspend fun getCurrentUser() = remoteProvider.getCurrentUser()
    suspend fun deleteNote(noteId: String) = remoteProvider.deleteNote(noteId)

}