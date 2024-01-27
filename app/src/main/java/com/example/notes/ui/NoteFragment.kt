package com.example.notes.ui

import android.os.Bundle
import androidx.navigation.Navigation
import com.example.notes.R
import com.example.notes.adapter.NotesAdapter
import com.example.notes.model.NoteDatabase
import com.example.notes.note.BaseFragment
import kotlinx.android.synthetic.main.fragment_notes.*
import kotlinx.coroutines.launch

class NoteFragment : BaseFragment() {

    override fun initComponents() {
        recycler.setHasFixedSize(false)

        launch {
            context?.let {
                val notes = NoteDatabase(it).getNoteDao().getAllNotes()
                recycler.adapter = NotesAdapter(notes)
            }
            btAdd.setOnClickListener {
                val action = NoteFragmentDirections.noteAdd()
                Navigation.findNavController(it).navigate(action)
            }

        }
    }

    override fun getLayoutId(): Int {
      return R.layout.fragment_notes
    }


}