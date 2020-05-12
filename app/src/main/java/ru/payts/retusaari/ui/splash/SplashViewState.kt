package ru.payts.retusaari.ui.splash

import ru.payts.retusaari.ui.base.BaseViewState

class SplashViewState(authenticated: Boolean? = null, error: Throwable? = null): BaseViewState<Boolean?>(authenticated, error)