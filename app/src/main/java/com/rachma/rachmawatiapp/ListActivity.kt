package com.rachma.rachmawatiapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.rachma.rachmawatiapp.note.Note

// Untuk mendeklarasikan class yang bernama list activity
class ListActivity : AppCompatActivity(), NoteClickInterface, NoteClickDeleteInterface {

    // Untuk mendeklarasikan variabel yang bernama viewModal
    // Untuk mendeklarasikan variabel yang bernama notesRV untuk recyclerview
    // Untuk mendeklarasikan variabel yang bernama addFAB.
    lateinit var viewModal: NoteViewModal
    lateinit var notesRV: RecyclerView
    lateinit var addFAB: FloatingActionButton

    // Untuk memanggil kelas super onCreate dalam pembuatan activity ini
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        // Untuk menginisialisasi variabel yang bernama notesRV dan addFAB
        notesRV = findViewById(R.id.notesRV)
        addFAB = findViewById(R.id.idFAB)

        // Untuk mengatur layout manager untuk recyclerview
        notesRV.layoutManager = LinearLayoutManager(this)

        // Untuk menginisialisasi class adapter yang bernama noteRVAdapter
        val noteRVAdapter = NoteRVAdapter(this, this, this)

        // Untuk mengatur adapter pada recyclerview
        notesRV.adapter = noteRVAdapter

        // Untuk menginisialisasi view modal
        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModal::class.java)

        // Untuk memanggil method allnotes dari class viewModal untuk mengamat perubahan pada list
        viewModal.allNotes.observe(this, Observer { list ->
            list?.let {
                // Untuk mengupdate list
                noteRVAdapter.updateList(it)
            }
        })

        // Untuk menambahkan klik listener pada addFAB
        // Untuk membuka intent baru untuk menambahkan data baru
        addFAB.setOnClickListener {
            val intent = Intent(this@ListActivity, AddEditNoteActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    // Untuk memanggil method onNoteClick
    override fun onNoteClick(note: Note) {
        // Untuk membuka intent baru dan memberikan data kesana.
        val intent = Intent(this@ListActivity, AddEditNoteActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteTitle", note.noteTitle)
        intent.putExtra("noteDescription", note.noteDescription)
        intent.putExtra("noteId", note.id)
        startActivity(intent)
        this.finish()
    }

    // Untuk memanggil method onDeleteIconClick
    override fun onDeleteIconClick(note: Note) {
        // Untuk memanggil method delete dari view modal untuk menghapus data atau tidak
        viewModal.deleteNote(note)

        // Untuk menampilkan pesan dalam bentuk toast
        Toast.makeText(this, "${note.noteTitle} Deleted", Toast.LENGTH_LONG).show()
    }

}