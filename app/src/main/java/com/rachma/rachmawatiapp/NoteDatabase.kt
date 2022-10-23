package com.rachma.rachmawatiapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rachma.rachmawatiapp.note.Note

// Untuk mendeklarasikan anotasi database yang mempunyai entiti berupa array untuk bagian class note, dengan versi berupa 1
@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
// Untuk mendeklarasikan abstrak kelas yang bernama Notedatabase
abstract class NoteDatabase : RoomDatabase() {
    abstract fun getNotesDao(): NotesDao

    companion object {

        // Untuk mencegah beberapa pembukaan database diwaktu yang sama
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        // Untuk mendeklarasikan fungsi yang bernama getDatabase
        fun getDatabase(context: Context): NoteDatabase {

            // Jika instance bukan null maka kembalikan
            // Dan jika instance berupa null maka membuat database
            // Database yang dibuat bernama note_database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database"
                ).build()
                INSTANCE = instance

                // Untuk mengembalikan instance
                instance
            }
        }
    }


}