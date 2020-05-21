package ru.payts.retusaari.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import ru.payts.retusaari.data.NotesRepository
import ru.payts.retusaari.data.entity.Note
import ru.payts.retusaari.data.model.NoteResult
import ru.payts.retusaari.ui.note.NoteViewModel
import ru.payts.retusaari.ui.note.NoteViewState

class NoteViewModelTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private val mockRepository = mockk<NotesRepository>()
    private val noteLiveData = MutableLiveData<NoteResult>()

    private val testNote = Note("1", "title1", "text1")
    private lateinit var viewModel: NoteViewModel

    @Before
    fun setup() {
        clearAllMocks()
        every { mockRepository.getNoteById(testNote.id) } returns noteLiveData
        every { mockRepository.deleteNote(testNote.id) } returns noteLiveData
        viewModel = NoteViewModel(mockRepository)
    }


    @Test
    fun `loadNote should return Note Data`() {
        var result: NoteViewState.Data? = null
        val testData = NoteViewState.Data(false, testNote)
        viewModel.getViewState().observeForever {
            result = it?.data
        }
        viewModel.loadNote(testNote.id)
        noteLiveData.value = NoteResult.Success(testNote)
        assertEquals(testData, result)
    }

    @Test
    fun `loadNote should return error`() {
        var result: Throwable? = null
        val testData = Throwable("error")
        viewModel.getViewState().observeForever {
            result = it?.error
        }
        viewModel.loadNote(testNote.id)
        noteLiveData.value = NoteResult.Error(error = testData)
        assertEquals(testData, result)
    }

    @Test
    fun `deleteNote should return Note Data with isDeleted`() {
        var result: NoteViewState.Data? = null
        val testData = NoteViewState.Data(true, null)
        viewModel.getViewState().observeForever {
            result = it?.data
        }
        viewModel.save(testNote)
        viewModel.deleteNote()
        noteLiveData.value = NoteResult.Success(null)
        assertEquals(testData, result)
    }

    @Test
    fun `deleteNote should return error`() {
        var result: Throwable? = null
        val testData = Throwable("error")
        viewModel.getViewState().observeForever {
            result = it?.error
        }
        viewModel.save(testNote)
        viewModel.deleteNote()
        noteLiveData.value = NoteResult.Error(error = testData)
        assertEquals(testData, result)
    }


//    @Test
//    fun `should save changes`() {
//        viewModel.save(testNote)
//        viewModel.onCleared()
//        verify(exactly = 1) { mockRepository.saveNote(testNote) }
//    }
}