package ru.payts.retusaari.ui.note

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.activity_note.*
import org.koin.android.viewmodel.ext.android.viewModel
import ru.payts.retusaari.R
import ru.payts.retusaari.common.format
import ru.payts.retusaari.common.getColorInt
import ru.payts.retusaari.data.entity.Note
import ru.payts.retusaari.ui.base.BaseActivity
import java.util.*

class NoteActivity : BaseActivity<NoteViewState.Data, NoteViewState>() {

    companion object {

        private const val EXTRA_NOTE = "extra.NOTE"
        private const val DATE_FORMAT = "dd.MM.yy HH:mm"

        fun start(context: Context, noteId: String? = null) =
            Intent(context, NoteActivity::class.java).run {
                putExtra(EXTRA_NOTE, noteId)
                context.startActivity(this)
            }
    }

    var color = Note.Color.WHITE
    private var note: Note? = null
    override val layoutRes: Int = R.layout.activity_note
    override val model: NoteViewModel by viewModel()

    val textChangeListener = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            saveNote()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val noteId = intent.getStringExtra(EXTRA_NOTE)
        noteId?.let {
            model.loadNote(it)
        } ?: let {
            initView()
        }

    }

    override fun renderData(data: NoteViewState.Data) {
        if (data.isDeleted) finish()
        this.note = data.note
        initView()
    }


    private fun initView() {
        supportActionBar?.title = note?.let {
            it.lastChanged.format(DATE_FORMAT)
        } ?: getString(R.string.new_note_title)

        et_title.removeTextChangedListener(textChangeListener)
        et_body.removeTextChangedListener(textChangeListener)

        note?.let { note ->
            if (et_title.text.toString() != note.title) et_title.setText(note.title)
            if (et_body.text.toString() != note.text) et_body.setText(note.text)
            color = note.color
            toolbar.setBackgroundColor(note.color.getColorInt(this))
        }

        et_title.addTextChangedListener(textChangeListener)
        et_body.addTextChangedListener(textChangeListener)

        colorPicker.onColorClickListener = {
            color = it
            toolbar.setBackgroundColor(color.getColorInt(this))
            saveNote()
        }
    }


    private fun saveNote() {
        if (et_title.text == null || et_title.text!!.length < 3) return

        note = note?.copy(
            title = et_title.text.toString(),
            text = et_body.text.toString(),
            lastChanged = Date(),
            color = color
        ) ?: Note(UUID.randomUUID().toString(), et_title.text.toString(), et_body.text.toString())

        note?.let {
            model.save(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?) =
        menuInflater.inflate(R.menu.note, menu).let { true }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> onBackPressed().let { true }
        R.id.delete -> deleteNote().let { true }
        R.id.pallete -> togglePallete().let { true }
        else -> super.onOptionsItemSelected(item)
    }


    fun deleteNote() {
        AlertDialog.Builder(this)
            .setMessage(R.string.note_delete_message)
            .setNegativeButton(R.string.note_delete_cancel) { dialog, which ->
                dialog.dismiss()
            }
            .setPositiveButton(R.string.note_delete_ok) { dialog, which ->
                model.deleteNote()
            }
            .show()
    }

    fun togglePallete() {
        if (colorPicker.isOpen) {
            colorPicker.close()
        } else {
            colorPicker.open()
        }
    }

}
