package ru.payts.retusaari.ui.splash

import ru.payts.retusaari.data.NotesRepository
import ru.payts.retusaari.data.errors.NoAuthException
import ru.payts.retusaari.ui.base.BaseViewModel

class SplashViewModel : BaseViewModel<Boolean?, SplashViewState>() {

    fun requestUser() {
        NotesRepository.getCurrentUser().observeForever {
            viewStateLiveData.value = it?.let {
                SplashViewState(true)
            } ?: let {
                SplashViewState(error = NoAuthException())
            }
        }
    }

}