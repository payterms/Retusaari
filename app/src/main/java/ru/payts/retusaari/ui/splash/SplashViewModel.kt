package ru.payts.retusaari.ui.splash

import kotlinx.coroutines.launch
import ru.payts.retusaari.data.NotesRepository
import ru.payts.retusaari.data.errors.NoAuthException
import ru.payts.retusaari.ui.base.BaseViewModel

class SplashViewModel(val notesRepository: NotesRepository) : BaseViewModel<Boolean?>() {

    fun requestUser() {
        launch {
            notesRepository.getCurrentUser()?.let {
                setData(true)
            } ?: setError(NoAuthException())
        }
    }
}

