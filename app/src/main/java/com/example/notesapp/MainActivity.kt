package com.example.notesapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), INotesRVAdapter {

    private lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val edtNote = findViewById<EditText>(R.id.edittext)
        val btnSave = findViewById<Button>(R.id.button)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NoteAdapter(this, this)
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this) { list ->
            list?.let {
                adapter.updateList(list)
            }

        }
        btnSave.setOnClickListener {

            val noteText = edtNote.text.toString()
            if (noteText.isNotEmpty()) {
                viewModel.insert(Note(noteText))
                Toast.makeText(this, "$noteText Inserted", Toast.LENGTH_SHORT).show();
            }
        }
    }

        override fun onItemClicked(note: Note) {
            viewModel.delete(note)
        }
    }

