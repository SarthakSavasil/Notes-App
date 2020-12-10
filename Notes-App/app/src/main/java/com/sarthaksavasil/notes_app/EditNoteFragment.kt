package com.sarthaksavasil.notes_app

import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.sarthaksavasil.notes_app.db.Notes
import com.sarthaksavasil.notes_app.db.NotesDB
import kotlinx.android.synthetic.main.fragment_edit_note.*

class EditNoteFragment : Fragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        button_save.setOnClickListener {
            val noteTitle = title.text.toString().trim()
            val noteBody = body.text.toString().trim()

            if(noteTitle.isEmpty()) {
                title.error = "Title Required"
                title.requestFocus()
                return@setOnClickListener
            }

            val notes = Notes(noteTitle,noteBody)
            //NotesDB(requireActivity()).getNoteDao().addNote(notes)
            //need to implement asyn task
            savedata(notes)


        }
       // NotesDB(activity!!).getNoteDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_note, container, false)
    }

    private fun savedata(notes: Notes){
        class SaveNote:AsyncTask<Void,Void,Void>() {
            override fun doInBackground(vararg params: Void?): Void? {
                NotesDB(requireActivity()).getNoteDao().addNote(notes)
                return null

            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                Toast.makeText(activity,"Saved",Toast.LENGTH_LONG).show()
            }
        }
        SaveNote().execute()
    }
}