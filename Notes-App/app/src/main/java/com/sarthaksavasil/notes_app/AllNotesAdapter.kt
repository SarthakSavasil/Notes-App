package com.sarthaksavasil.notes_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.sarthaksavasil.notes_app.db.Notes
import kotlinx.android.synthetic.main.note_layout.view.*


class AllNotesAdapter(private val notelist:List<Notes>): RecyclerView.Adapter<AllNotesAdapter.AllNotesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllNotesViewHolder {

        return AllNotesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.note_layout,parent,false)
        )
    }

    override fun getItemCount(): Int = notelist.size

    override fun onBindViewHolder(holder: AllNotesViewHolder, position: Int) {
        holder.view.card_title.text = notelist[position].title
        holder.view.card_body.text = notelist[position].note

        holder.view.setOnClickListener {
            val action = HomeFragmentDirections.actionToedit()
            action.notes = notelist[position]
            Navigation.findNavController(it).navigate(action)

        }
    }
    class AllNotesViewHolder(val view:View): RecyclerView.ViewHolder(view)
}