package ru.payts.retusaari.ui.note

import ru.payts.retusaari.data.entity.Note
import ru.payts.retusaari.ui.base.BaseViewState

class NoteViewState(val note: Note? = null, error: Throwable? = null) : BaseViewState<Note?>(note, error)