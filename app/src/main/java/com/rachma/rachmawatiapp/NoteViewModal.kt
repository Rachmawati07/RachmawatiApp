package com.rachma.rachmawatiapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.rachma.rachmawatiapp.note.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// Untuk mendeklarasikan class yang bernamaNoteViewModal
class NoteViewModal(application: Application) : AndroidViewModel(application) {

    // Untuk mendeklarasikan variabel yang bernama allnotes
    // Untuk mendeklarasikan variabel yang bernama repository
    val allNotes: LiveData<List<Note>>
    val repository: NoteRepository

    // Untuk menginisialisasi dao, repository dan allnotes
    init {
        val dao = NoteDatabase.getDatabase(application).getNotesDao()
        repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }

    // Untuk mendeklarasikan fungsi yang bernama deleteNote
    // Untuk memanggil metode delete dari repositori  untuk menghapus data
    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    // Untuk mendeklarasikan fungsi yang bernama updateNote
    // Untuk memanggil metode delete dari repository untuk mengupdate data
    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)
    }

    // Untuk mendeklarasikan fungsi yang bernama addNote
    // Untuk memanggil metode insert dari repository untuk menambahkan data
    fun addNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
}