package ru.payts.retusaari.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.payts.retusaari.data.entity.Note
import java.util.*

object NotesRepository {
    private val notesLiveData = MutableLiveData<List<Note>>()

    private val notes = mutableListOf(
        Note(
            UUID.randomUUID().toString(),
            "Первая заметка",
            "Текст первой заметки. Короткий, но интересный",
            color = Note.Color.WHITE
        ),
        Note(
            UUID.randomUUID().toString(),
            "Вторая заметка",
            "Текст второй заметки. Короткий, но интересный",
            color = Note.Color.YELLOW
        ),
        Note(
            UUID.randomUUID().toString(),
            "Третья заметка",
            "Текст третьей заметки. Короткий, но интересный",
            color = Note.Color.GREEN
        ),
        Note(
            UUID.randomUUID().toString(),
            "Четвертая заметка",
            "Текст четвертой заметки. Короткий, но интересный",
            color = Note.Color.BLUE
        ),
        Note(
            UUID.randomUUID().toString(),
            "Пятая заметка",
            "Текст пятой заметки. Короткий, но интересный",
            color = Note.Color.RED
        ),
        Note(
            UUID.randomUUID().toString(),
            "Шестая заметка",
            "Текст шестой заметки. Короткий, но интересный",
            color = Note.Color.VIOLET
        )
    )

    init {
        notesLiveData.value = notes
    }

    fun getNotes(): LiveData<List<Note>> {
        return notesLiveData
    }

    fun saveNote(note: Note) {
        addOrReplace(note)
        notesLiveData.value = notes
    }

    private fun addOrReplace(note: Note) {
        for (i in notes.indices) {
            if (notes[i] == note) {
                notes[i] = note
                return
            }
        }
        notes.add(note)
    }

}