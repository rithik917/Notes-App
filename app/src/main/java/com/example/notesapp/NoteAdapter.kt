package com.example.notesapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(private val context: Context, private val listener: INotesRVAdapter) : RecyclerView.Adapter<NoteAdapter.NotesViewHolder>() {

    private val allNotes = ArrayList<Note>()

    class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtItem: TextView = itemView.findViewById(R.id.name)
        val ivDelete: ImageView = itemView.findViewById(R.id.image2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val viewHolder = NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.note_item, parent, false))

        viewHolder.ivDelete.setOnClickListener{
            listener.onItemClicked(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val currentNote = allNotes[position]
        holder.txtItem.text = currentNote.text
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateList(newList: List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
}

interface INotesRVAdapter {
    fun onItemClicked(note: Note)
}