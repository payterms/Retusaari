package ru.payts.retusaari.ui.splash

import ru.payts.retusaari.data.NotesRepository
import ru.payts.retusaari.data.errors.NoAuthException
import ru.payts.retusaari.ui.base.BaseViewModel

class SplashViewModel(val notesRepository: NotesRepository) : BaseViewModel<Boolean?, SplashViewState>() {

    fun requestUser() {
        notesRepository.getCurrentUser().observeForever {
            viewStateLiveData.value = it?.let {
                SplashViewState(true)
            } ?: let {
                SplashViewState(error = NoAuthException())
            }
        }
    }

}