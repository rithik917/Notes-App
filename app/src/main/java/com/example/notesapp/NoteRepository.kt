package com.example.notesapp

import androidx.lifecycle.LiveData
import androidx.room.Dao

class NoteRepository(private val noteDao: dao) {
    val allnote:LiveData<List<Note>> =noteDao.getNotes()
    suspend fun insert(note: Note){
        noteDao.insert(note)
    }
    suspend fun delete(note: Note){
        noteDao.delete(note)
    }
}