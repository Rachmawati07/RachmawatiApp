package com.rachma.rachmawatiapp.quotes

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.rachma.rachmawatiapp.data.Note
import kotlinx.coroutines.*


// Untuk mendeklarasikan class yang bernama ListViewModel
class ListViewModel(application: Application): AndroidViewModel(application) {
    // Untuk mendeklarasikan variabel notes untuk live data
    // Untuk mendeklaraskkan variabel db
    private val notes: MutableLiveData<List<Note>> = MutableLiveData()
    private var db: FirebaseFirestore = Firebase.firestore

    // Untuk mendeklarasikan fungsi yang bernama getNotes untuk live data
    fun getNotes(): LiveData<List<Note>> {
        return notes
    }

    // Untuk mendeklarasikan fungsi yang bernama getData
    // Untuk menampilkan data
    fun getData(){

        // Untuk menginisialisasi collection dengan nama db
        db.collection("notes")
            .get()
            .addOnSuccessListener { result ->
                val tempNotes = arrayListOf<Note>()
                for (document in result) {
                    document.data.map { (key, value) -> tempNotes.add(Note(document.id, value.toString())) }
                }
                notes.postValue(tempNotes)
            }
            .addOnFailureListener { exception ->
                Log.w("MainActivity", "Error getting documents.", exception)
            }
    }

    // Untuk mendeklarasikan fungsi yang bernama addNote
    fun addNote(note: Note){
        CoroutineScope(Dispatchers.IO).launch {
            // Untuk mendeklarasikan variabel yang bernama newNote
            val newNote = hashMapOf(
                "noteText" to note.noteText,
            )
            // Untuk menambahkan data pada collecttion dan akan mendapatkan datanya
            db.collection("notes").add(newNote)
            getData()
        }
    }

    // Untuk mendeklarasikan fungsi yang bernama deleteNote
    fun deleteNote(noteID: String){
        CoroutineScope(Dispatchers.IO).launch {
            db.collection("notes")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        if(document.id == noteID){
                            // Untuk menghapus data pada collection
                            db.collection("notes").document(noteID).delete()
                        }
                    }
                    getData()
                }
                .addOnFailureListener { exception ->
                    Log.w("MainActivity", "Error getting documents.", exception)
                }
        }
    }
}