package com.rachma.rachmawatiapp

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Update
import com.rachma.rachmawatiapp.note.Note

// Untuk mendeklarasikan anotasi Dao
// Untuk mendeklarasikan interface yang bernama NotesDao
@Dao
interface NotesDao {

    // Untuk mendeklarasikan method inset
    // Yang akan berfungsi untuk menambahkan data pada database
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note :Note)

    // Untuk mendeklarasikan method update
    // Yang akan berfungsi untuk mengupdate data pada database
    @Update
    fun update(note: Note)

    // Untuk mendeklarasikan method yang bernama delete
    // Yang akan berfungsi untuk menghapus data dari database
    @Delete
    fun delete(note: Note)

    // Untuk mendeklarasikan query
    // Untuk menyeleksi semua data dari tabel notesTable dan diurutkan berdasarkan id secara ascending
    // Dan semua data akan didapatkan dengan menggunakan live data
    @Query("Select * from notesTable order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>


}