package com.rachma.rachmawatiapp.quotes

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.rachma.rachmawatiapp.R

// Untuk mendeklarasikan class yang bernama Update
class Update : Fragment() {

    // Untuk memanggil kelas super onCreate dalam pembuatan activity ini
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Untuk menginflate layout untuk fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        // Untuk mendeklarasikan sharedPreferences
        // Untuk menggunakan requireActivity untuk mengakses MainActivity
        val sharedPreferences = requireActivity().getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE)

        // Untuk mendeklarasikan variabel updateViewModel
        val updateViewModel = ViewModelProvider(this).get(UpdateViewModel::class.java)

        // Untuk mendeklarasikan variabel etNote dan btUpdate
        val etNote = view.findViewById<EditText>(R.id.etNoteUpdate)
        val btUpdate = view.findViewById<Button>(R.id.btNoteUpdate)

        // Untuk mendeklarasikan klik listener pada btUpdate
        btUpdate.setOnClickListener {

            // Untuk mendeklarasikan variabel yang bernama noteId
            // Untuk mendapatkan data NoteId
            val noteId = sharedPreferences.getString("NoteId", "").toString()
            updateViewModel.editNote(noteId, etNote.text.toString())
            with(sharedPreferences.edit()) {
                putString("NoteId", etNote.text.toString())
                apply()
            }
            findNavController().navigate(R.id.action_update_to_list)
        }

        return view
    }
}