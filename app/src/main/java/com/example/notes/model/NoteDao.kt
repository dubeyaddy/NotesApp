package com.example.notes.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Insert
   suspend fun addNote(note: Note)

    @Query("SELECT * FROM note ORDER BY id DESC")
   suspend fun getAllNotes():List<Note>

    @Insert
   suspend fun addMultipleNotes(vararg note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)



}