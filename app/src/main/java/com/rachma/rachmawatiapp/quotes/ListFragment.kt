package com.rachma.rachmawatiapp.quotes

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rachma.rachmawatiapp.R
import com.rachma.rachmawatiapp.adapters.NoteAdapter
import com.rachma.rachmawatiapp.data.Note
import kotlinx.coroutines.*

// Untuk mendeklarasikan class yang bernama ListFragment
class ListFragment : Fragment() {

    // Untuk menginisialisasi variabel evNotes untuk recycler vuew
    // Untuk menginisialisasi variabel rvAdapter untuk NoteAdapter
    // Untuk menginisialisasi variabel editText untuk EditText
    // Untuk menginisialisasi variabel submitBtn untuk button
    private lateinit var rvNotes: RecyclerView
    private lateinit var rvAdapter: NoteAdapter
    private lateinit var editText: EditText
    private lateinit var submitBtn: Button

    // Untuk menginisialisasi variabel listViewModel dan notes
    lateinit var listViewModel: ListViewModel
    private lateinit var notes: List<Note>

    // Untuk menginisialisasi sharedPreferences
    lateinit var sharedPreferences: SharedPreferences

    // Untuk memanggil kelas super onCreate dalam pembuatan activity ini
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Untuk mendeklarasikan variabel view
        // Untuk inflate layout untuk fragment_list
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        // Untuk mengakses MainActivity menggunakan method requireActivity
        sharedPreferences = requireActivity().getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE)

        // Untuk mendeklarasikan notes untuk array
        notes = arrayListOf()

        // Untuk menginisialiasi listViewModel untuk mendapatkan Notes
        listViewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        listViewModel.getNotes().observe(viewLifecycleOwner, {
                notes -> rvAdapter.update(notes)
        })

        // Untuk menginisialisasi variabel edit text dan submitbtn
        editText = view.findViewById(R.id.tvNewNote)
        submitBtn = view.findViewById(R.id.btSubmit)

        // Untuk memberikan memberikan klik listener pada submit btn
        submitBtn.setOnClickListener {
            // Untuk menginisialisasi variabel listViewModel untuk menambahkan data
            listViewModel.addNote(Note("", editText.text.toString()))

            // Untuk menghapus teks di edit text
            editText.text.clear()
            editText.clearFocus()
        }

        // Untuk menginisialisasi variabel rvNotes, rvAdapter
        // Untuk mendapatkan data dari listViewModel
        rvNotes = view.findViewById(R.id.rvNotes)
        rvAdapter = NoteAdapter(this)
        rvNotes.adapter = rvAdapter
        rvNotes.layoutManager = LinearLayoutManager(requireContext())

        listViewModel.getData()

        return view
    }

    // Untuk memanggil method onResume
    override fun onResume() {
        super.onResume()
        // Untuk memanggil fungsi 'getData' dari ViewModel setelah penundaan satu detik
        CoroutineScope(Dispatchers.IO).launch {
            delay(1000)
            listViewModel.getData()
        }
    }
}