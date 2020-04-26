package ru.payts.retusaari.data

import ru.payts.retusaari.data.entity.Note

object NotesRepository {
    val notes: List<Note> = listOf(
        Note(
            "Первая заметка",
            "Текст первой заметки. Короткий, но интересный",
            0xfff06292.toInt()
        ),
        Note(
            "Вторая заметка",
            "Текст второй заметки. Короткий, но интересный",
            0xff9575cd.toInt()
        ),
        Note(
            "Третья заметка",
            "Текст третьей заметки. Короткий, но интересный",
            0xff64b5f6.toInt()
        ),
        Note(
            "Четвертая заметка",
            "Текст четвертой заметки. Короткий, но интересный",
            0xff4db6ac.toInt()
        ),
        Note(
            "Пятая заметка",
            "Текст пятой заметки. Короткий, но интересный",
            0xffb2ff59.toInt()
        ),
        Note(
            "Шестая заметка",
            "Текст шестой заметки. Короткий, но интересный",
            0xffffeb3b.toInt()
        )
    )

//    fun getNotes(): List<Note> {
//        return notes
//    }
}