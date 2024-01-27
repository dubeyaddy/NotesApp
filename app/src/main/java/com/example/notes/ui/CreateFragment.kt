package com.example.notes.ui

import android.app.AlertDialog
import android.content.Intent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.notes.R
import com.example.notes.model.Note
import com.example.notes.model.NoteDatabase
import com.example.notes.note.BaseFragment
import kotlinx.android.synthetic.main.fragment_create_new_label.*
import kotlinx.coroutines.launch

class CreateFragment : BaseFragment(), View.OnClickListener {
    private var note: Note? = null

    override fun initComponents() {
        btAdd.setOnClickListener(this)
        setHasOptionsMenu(true)
        arguments.let {
            note = CreateFragmentArgs.fromBundle(it!!).note
            teTitle.setText(note?.title)
            teDescription.setText(note?.note)
        }

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_create_new_label
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btAdd -> {
                val title = teTitle.text.toString().trim()
                val noteBody = teDescription.text.toString().trim()
                if(!title.isEmpty())
                if (title.isEmpty()) {
                    teTitle.error = "Title required"
                    teTitle.requestFocus()
                }
                if (noteBody.isEmpty()) {
                    teDescription.error = "Note required"
                    teDescription.requestFocus()
                }
                launch {
                    context?.let {
                        val mNote = Note(title,noteBody)
                        if (note == null){
                            NoteDatabase(it).getNoteDao().addNote(mNote)
                            Toast.makeText(context, "Note saved", Toast.LENGTH_LONG).show()
                        }else
                        {
                            mNote.id = note!!.id
                            NoteDatabase(it).getNoteDao().updateNote(mNote)
                            Toast.makeText(context, "Note updated", Toast.LENGTH_LONG).show()
                        }
                        val action = CreateFragmentDirections.actionSave()
                        Navigation.findNavController(requireView()).navigate(action)
                    }
                }
            }
        }

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.bottom_nav_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete -> if (note !=null)
            {
                deleteNote()
            } else
            {
                Toast.makeText(context, "Cant delete", Toast.LENGTH_LONG).show()
            }
            R.id.share ->
                if (note!=null)
                {
                shareNote()
            }
            else{
                Toast.makeText(context,"Can't share the data",Toast.LENGTH_LONG).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun deleteNote(){
        AlertDialog.Builder(context).apply {
            setTitle("Are you sure?")
            setPositiveButton("Yes"){ _,_->
                launch {
                    NoteDatabase(context).getNoteDao().deleteNote(note!!)
                    val action = CreateFragmentDirections.actionSave()
                    Navigation.findNavController(requireView()).navigate(action)

                }
            }
            setNegativeButton("No") { _, _ ->
            }
        }.create().show()
    }
    private fun shareNote(){
        val t = note?.title
        val d = note?.note
        val shareIntent = Intent().apply {
            this.action = Intent.ACTION_SEND_MULTIPLE
            this.putExtra(Intent.EXTRA_TEXT, note?.note )
            this.type = "text/plan"
        }
        startActivity(shareIntent)
    }

}
