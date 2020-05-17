package ru.payts.retusaari.ui.note

import ru.payts.retusaari.data.entity.Note
import ru.payts.retusaari.ui.base.BaseViewState

class NoteViewState(data: Data = Data(), error: Throwable? = null) : BaseViewState<NoteViewState.Data>(data, error){
    data class Data(val isDeleted: Boolean = false, val note: Note? = null)
}