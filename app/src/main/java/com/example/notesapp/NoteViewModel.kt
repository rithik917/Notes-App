package com.example.notesapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.RoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application):AndroidViewModel(application) {
    val allNotes:LiveData<List<Note>>
    val repository:NoteRepository
    init{
        val db=NoteDatabase.getDatabase(application).Dao()
        repository=NoteRepository(db)
        allNotes=repository.allnote
    }
    fun delete(note:Note)=viewModelScope.launch(Dispatchers.IO){
//        this is used to call the suspend function of repository so that it runs in background
repository.delete(note)
    }
    fun insert(note:Note)=viewModelScope.launch(Dispatchers.IO){
        repository.insert(note)
    }
}