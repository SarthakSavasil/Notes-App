package com.sarthaksavasil.notes_app

import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sarthaksavasil.notes_app.db.Notes
import com.sarthaksavasil.notes_app.db.NotesDB
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(),IOnBackPressed{


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        getdata()

        button_add.setOnClickListener {
            val action = HomeFragmentDirections.actionToedit()
            Navigation.findNavController(it).navigate(action)
        }

    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
//
//        }


    private fun getdata(){
        recycler_view_note.setHasFixedSize(true)
        recycler_view_note.layoutManager = StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)
        class getNote: AsyncTask<Void, Void, Void>() {
            override fun doInBackground(vararg params: Void?): Void? {
               val noteList =  NotesDB(requireActivity()).getNoteDao().getAllNotes()
                recycler_view_note.adapter = AllNotesAdapter(noteList)
                if(noteList.size>0)
                    noview.visibility=View.GONE
                else
                    noview.visibility=View.VISIBLE
                return null

            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
//                Toast.makeText(activity,"Worked", Toast.LENGTH_LONG).show()
            }
        }
        getNote().execute()
    }
    override fun hBackPressed(): Boolean {

        return true

//        return if (myCondition) {
//            //action not popBackStack
//            true
//        } else {
//            false
//        }
    }
}