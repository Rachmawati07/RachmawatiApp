package com.rachma.rachmawatiapp.quotes

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// Untuk mendeklarasikan class yang bernama UpdateViewModel
class UpdateViewModel(application: Application): AndroidViewModel(application) {
    // Untuk mendeklarasikan variabel db
    private var db: FirebaseFirestore = Firebase.firestore

    // Untuk mendeklarasikan fungsi yang bernama editNote
    // Untuk mengedit data
    fun editNote(noteID: String, noteText: String){
        CoroutineScope(Dispatchers.IO).launch {
            db.collection("notes")
                .get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        if(document.id == noteID){
                            db.collection("notes").document(noteID).update("noteText", noteText)
                            break
                        }
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w("MainActivity", "Error getting documents.", exception)
                }
        }
    }
}