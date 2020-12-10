package com.sarthaksavasil.notes_app

import android.app.AlertDialog
import android.os.AsyncTask
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.room.Delete
import com.sarthaksavasil.notes_app.EditNoteFragmentDirections.actionSave
import com.sarthaksavasil.notes_app.db.Notes
import com.sarthaksavasil.notes_app.db.NotesDB
import kotlinx.android.synthetic.main.fragment_edit_note.*

class EditNoteFragment : Fragment() {
    private var note:Notes?=null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            note = EditNoteFragmentArgs.fromBundle(it).notes

            title.setText(note?.title)
            body.setText(note?.note)

        }


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

            val action = EditNoteFragmentDirections.actionSave()
            Navigation.findNavController(it).navigate(action)
        }
       // NotesDB(activity!!).getNoteDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        setHasOptionsMenu(true)


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_note, container, false)
    }

    private fun savedata(notes: Notes){
        class SaveNote:AsyncTask<Void,Void,Void>() {
            override fun doInBackground(vararg params: Void?): Void? {
                val mnote = notes
                if(note==null)
                {
                    NotesDB(requireActivity()).getNoteDao().addNote(notes)
                } else{
                    mnote.id = note!!.id
                    NotesDB(requireActivity()).getNoteDao().update(mnote)
                }
                return null

            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                Toast.makeText(activity,"Saved",Toast.LENGTH_LONG).show()
            }
        }
        SaveNote().execute()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_d -> if(note!=null) {
                deleteNote()

            }
            else
                Toast.makeText(context,"Can Not Delete",Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }
    fun deleteNote(){
            AlertDialog.Builder(context).apply {
                setTitle("Sure?")
                setMessage("Its a permanent Delete")
                setPositiveButton("Yes"){_,_ ->
                    deldata()
                    val action = EditNoteFragmentDirections.actionSave()
                    Navigation.findNavController(requireView()).navigate(action)

                }
                setNegativeButton("No"){_,_ ->

                }
            }.create().show()
    }
    private fun deldata(){
        class DelNote:AsyncTask<Void,Void,Void>() {
            override fun doInBackground(vararg params: Void?): Void? {
                    NotesDB(requireActivity()).getNoteDao().delNote(note!!)

                return null

            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                Toast.makeText(activity,"Deleted",Toast.LENGTH_SHORT).show()
            }
        }
        DelNote().execute()
    }
}