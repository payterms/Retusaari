package ru.payts.retusaari.data

import ru.payts.retusaari.data.provider.FirestoreProvider
import ru.payts.retusaari.data.provider.RemoteDataProvider
import ru.payts.retusaari.data.entity.Note

object NotesRepository {

    private val remoteProvider: RemoteDataProvider = FirestoreProvider()

    fun getNotes() = remoteProvider.subscribeToAllNotes()
    fun saveNote(note: Note) = remoteProvider.saveNote(note)
    fun getNoteById(id: String) = remoteProvider.getNoteById(id)
    fun getCurrentUser() = remoteProvider.getCurrentUser()
}