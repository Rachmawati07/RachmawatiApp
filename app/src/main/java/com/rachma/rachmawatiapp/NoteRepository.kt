package com.rachma.rachmawatiapp

import androidx.lifecycle.LiveData
import com.rachma.rachmawatiapp.note.Note

// Untuk mendeklarasikan class yang bernama NoteRepository
class NoteRepository(private val notesDao: NotesDao) {

    // Untuk membuat variabel yang bernama allNotes
    // Akan mendapatkan semua catatan dari kelas DAO menggunakan livedata
    val allNotes: LiveData<List<Note>> = notesDao.getAllNotes()

    // Untuk mendeklarasikan fungsi yang bernama insert
    // Untuk menambahkan data kedalam database
    fun insert(note: Note) {
        notesDao.insert(note)
    }

    // Untuk mendeklarasikan fungsi yang bernama delete
    // Untuk menghapus data dari database
    fun delete(note: Note){
        notesDao.delete(note)
    }

    // Untuk mendeklarasikan fungsi yang bernama update
    // Untuk mengupdate data pada database
    fun update(note: Note){
        notesDao.update(note)
    }
}