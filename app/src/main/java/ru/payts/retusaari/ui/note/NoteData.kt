package ru.payts.retusaari.ui.note

import ru.payts.retusaari.data.entity.Note

data class NoteData(val isDeleted: Boolean = false, val note: Note? = null)