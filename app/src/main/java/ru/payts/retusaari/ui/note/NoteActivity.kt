package ru.payts.retusaari.ui.note

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.activity_note.*
import ru.payts.retusaari.R
import ru.payts.retusaari.data.entity.Note
import java.text.SimpleDateFormat
import java.util.*

class NoteActivity : AppCompatActivity() {

    companion object {

        private const val EXTRA_NOTE = "extra.NOTE"
        private const val DATE_FORMAT = "dd.MM.yy HH:mm"

        fun start(context: Context, note: Note?) = Intent(context, NoteActivity::class.java).run {
            putExtra(EXTRA_NOTE, note)
            context.startActivity(this)
        }
    }


    private var note: Note? = null
    lateinit var viewModel: NoteViewModel

    val textChangeListener = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            saveNote()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        note = intent.getParcelableExtra(EXTRA_NOTE)

        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        supportActionBar?.title = note?.let {
            SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(it.lastChanged)
        } ?: getString(R.string.new_note_title)

        initView()
    }

    private fun initView() {
        et_title.removeTextChangedListener(textChangeListener)
        et_body.removeTextChangedListener(textChangeListener)

        note?.let { note ->
            et_title.setText(note.title)
            et_body.setText(note.text)
            val color = when (note.color) {
                Note.Color.WHITE -> R.color.white
                Note.Color.YELLOW -> R.color.yellow
                Note.Color.GREEN -> R.color.green
                Note.Color.BLUE -> R.color.blue
                Note.Color.RED -> R.color.red
                Note.Color.VIOLET -> R.color.violet
                Note.Color.PINK -> R.color.pink
            }
            //toolbar.setBackgroundColor(resources.getColor(color))
            toolbar.setBackgroundColor(ContextCompat.getColor(applicationContext,color))

        }

        et_title.addTextChangedListener(textChangeListener)
        et_body.addTextChangedListener(textChangeListener)
    }


    private fun saveNote() {
        if (et_title.text == null || et_title.text!!.length < 3) return

        note = note?.copy(
            title = et_title.text.toString(),
            text = et_body.text.toString(),
            lastChanged = Date()
        ) ?: Note(UUID.randomUUID().toString(), et_title.text.toString(), et_body.text.toString())

        note?.let {
            viewModel.save(it)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}
