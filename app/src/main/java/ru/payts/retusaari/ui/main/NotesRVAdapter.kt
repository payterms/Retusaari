package ru.payts.retusaari.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_note.view.*
import ru.payts.retusaari.R
import ru.payts.retusaari.common.getColorInt
import ru.payts.retusaari.data.entity.Note


class NotesRVAdapter(val onItemClick: ((Note) -> Unit)? = null) : RecyclerView.Adapter<NotesRVAdapter.ViewHolder>() {

    var notes: List<Note> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false))

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(notes[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(note: Note) = with(itemView) {
            tv_title.text = note.title
            tv_text.text = note.text

            setBackgroundColor(note.color.getColorInt(context))

            itemView.setOnClickListener {
                onItemClick?.invoke(note)
            }
        }
    }
}