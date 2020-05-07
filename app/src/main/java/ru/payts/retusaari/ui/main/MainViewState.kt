package ru.payts.retusaari.ui.main

import ru.payts.retusaari.data.entity.Note
import ru.payts.retusaari.ui.base.BaseViewState

class MainViewState(val notes: List<Note>? = null, error: Throwable? = null) :
    BaseViewState<List<Note>?>(notes, error)