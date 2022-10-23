package com.rachma.rachmawatiapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.rachma.rachmawatiapp.note.Note
import com.rachma.rachmawatiapp.quotes.MainActivity
import java.text.SimpleDateFormat
import java.util.*

// Untuk mendeklarasikan class yang bernama AddEditNoteActivity
class AddEditNoteActivity : AppCompatActivity() {
    // Untuk mendeklarasikan variabel yang bernama noteTitleEdt, noteEdt, saveBtn untuk komponen UUI
    lateinit var noteTitleEdt: EditText
    lateinit var noteEdt: EditText
    lateinit var saveBtn: Button

    // Untuk mendeklrasikan variabel yang bernama viewModal untuk NoteViewModal dan noteId yang memiliki nilai berupa integer -1
    lateinit var viewModal: NoteViewModal
    var noteID = -1;

    // Untuk memanggil kelas super onCreate dalam pembuatan activity ini
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_note)
        // Untuk menginisialisasi view modal
        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModal::class.java)

        // Untuk menginisialisasi variabel noteTitleEdt, noteEdt dan saveBtn
        noteTitleEdt = findViewById(R.id.idEdtNoteName)
        noteEdt = findViewById(R.id.idEdtNoteDesc)
        saveBtn = findViewById(R.id.idBtn)

        // Untuk mendeklarasikan variabel yang bernama noteType dan mendapatkan data yang diteruskan melalui intent
        val noteType = intent.getStringExtra("noteType")
        if (noteType.equals("Edit")) {

            // Untuk mendeklarasikan variabel yang bernama noteTitle dan noteDescriprion
            // Untuk mengatur data ke edit text
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDescription = intent.getStringExtra("noteDescription")
            noteID = intent.getIntExtra("noteId", -1)
            saveBtn.setText("Update Note")
            noteTitleEdt.setText(noteTitle)
            noteEdt.setText(noteDescription)
        } else {
            saveBtn.setText("Save Note")
        }

        // Untuk menambahkan klik listener pada saveBtn
        saveBtn.setOnClickListener {

            // Untuk mendeklarasikan variabel noteTitle dan noteDescription dan mendapatkan datanya dari edit text
            val noteTitle = noteTitleEdt.text.toString()
            val noteDescription = noteEdt.text.toString()

            // Untuk mengecek noteType
            // Apabila noteType dan noteDescription tidak kosong maka akan mengupdate data
            if (noteType.equals("Edit")) {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime: String = sdf.format(Date())
                    val updatedNote = Note(noteTitle, noteDescription, currentDateAndTime)
                    updatedNote.id = noteID
                    viewModal.updateNote(updatedNote)
                    // Untuk menampilkan pesan toast
                    Toast.makeText(this, "Note Updated..", Toast.LENGTH_LONG).show()
                }
            } else {
                // Apabila noteTitle dan noteDescription  tidak kosong maka akan menyimpan data
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime: String = sdf.format(Date())

                    // Dan jika string tidak kosong maka akan memanggil metode add note untuk menambahkan data pada room database
                    viewModal.addNote(Note(noteTitle, noteDescription, currentDateAndTime))
                    // Untuk menampilkan pesan toast
                    Toast.makeText(this, "$noteTitle Added", Toast.LENGTH_LONG).show()
                }
            }

            // Untuk memulai activity listActivity
            startActivity(Intent(applicationContext, ListActivity::class.java))
            this.finish()
        }
    }
}