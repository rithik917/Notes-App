package com.example.notesapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface dao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    if there are duplicate items then ignore
    suspend fun insert(note: Note)
    @Delete
//    it makes sure that database functions work only on the background thread and not on the ui thread
   suspend fun delete(note:Note)
    @Query("Select * from Notes_table order by id asc")
    fun getNotes():LiveData<List<Note>>
//    Live data acts as a wrapper above the data and makes the activity observe the data so that it get notified whenever the data is changed
}