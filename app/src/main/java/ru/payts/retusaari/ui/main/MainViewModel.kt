package ru.payts.retusaari.ui.main

import androidx.annotation.VisibleForTesting
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import ru.payts.retusaari.data.NotesRepository
import ru.payts.retusaari.data.entity.Note
import ru.payts.retusaari.data.model.NoteResult
import ru.payts.retusaari.ui.base.BaseViewModel

class MainViewModel(notesRepository: NotesRepository) : BaseViewModel<List<Note>?>() {

    private val noteChannel = notesRepository.getNotes()

    init {
        launch {
            noteChannel.consumeEach {
                when (it) {
                    is NoteResult.Success<*> -> setData(it.data as? List<Note>)
                    is NoteResult.Error -> setError(it.error)
                }
            }
        }
    }

    @VisibleForTesting
    public override fun onCleared() {
        super.onCleared()
        noteChannel.cancel()
    }
}