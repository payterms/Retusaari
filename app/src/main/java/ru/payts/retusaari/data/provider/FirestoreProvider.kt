package ru.payts.retusaari.data.provider

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import ru.payts.retusaari.data.entity.Note
import ru.payts.retusaari.data.model.NoteResult
import timber.log.Timber

class FirestoreProvider : RemoteDataProvider {

    companion object {
        private const val NOTES_COLLECTION = "notes"
    }

    private val store = FirebaseFirestore.getInstance()
    private val notesReference = store.collection(NOTES_COLLECTION)

    override fun saveNote(note: Note): LiveData<NoteResult> {
        val result = MutableLiveData<NoteResult>()
        notesReference.document(note.id)
            .set(note).addOnSuccessListener {
                Timber.d("Note $note is saved")
                result.value = NoteResult.Success(note)
            }.addOnFailureListener {
                Timber.e("Error saving note $note, message : ${it.message} ")
                result.value = NoteResult.Error(it)
            }

        return result
    }

    override fun getNoteById(id: String): LiveData<NoteResult> {
        val result = MutableLiveData<NoteResult>()
        notesReference.document(id)
            .get().addOnSuccessListener { snapshot ->
                result.value = NoteResult.Success(snapshot.toObject(Note::class.java))
            }.addOnFailureListener {
                result.value = NoteResult.Error(it)
            }
        return result
    }

    override fun subscribeToAllNotes(): LiveData<NoteResult> {
        val result = MutableLiveData<NoteResult>()
        notesReference.addSnapshotListener { querySnapshot, e ->
            e?.let {
                result.value = NoteResult.Error(e)
            } ?: let {
                querySnapshot?.let {
                    val notes = querySnapshot.map {
                        it.toObject(Note::class.java)
                    }
                    result.value = NoteResult.Success(notes)
                }
            }
        }
        return result
    }


}