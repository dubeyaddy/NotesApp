package com.example.notes.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.model.Note
import com.example.notes.ui.NoteFragmentDirections
import kotlinx.android.synthetic.main.item_notes.view.*
import java.util.*


class NotesAdapter(val notes: List<Note>) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {
    class NoteViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_notes, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return notes.size

    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) { 
        holder.view.card_view.setCardBackgroundColor(getRandomcolorCode())
        holder.view.tvTitle.text = notes[position].title
        holder.view.tvDescription.text = notes[position].note
        holder.view.setOnClickListener {
            val action = NoteFragmentDirections.noteAdd()
            action.note = notes[position]
            Navigation.findNavController(it).navigate(action)
        }
    }


    private fun getRandomcolorCode(): Int {
        val random = Random()
        return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
    }
}


