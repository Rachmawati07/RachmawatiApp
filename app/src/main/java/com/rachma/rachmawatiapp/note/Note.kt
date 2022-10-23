package com.rachma.rachmawatiapp.note

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Untuk mendeklarasikan entitas yang memuat nama tabelnya yaitu notesTabel
@Entity(tableName = "notesTable")

// Untuk mendeklarasikan class yang bernama Note
// Untuk mendeklarasikan nama kolom yang berupa title, descriptipn dan timestamp
// Untuk mendeklarasikan variabel yang bernama noteTitle, noteDescribtion dan timestamp dengan tipe data berupa string
class Note (@ColumnInfo(name = "title")val noteTitle :String,@ColumnInfo(name = "description")val noteDescription :String,@ColumnInfo(name = "timestamp")val timeStamp :String) {

    // Untuk menentukan primary keynya yaitu id
    // Untuk mendeklarasikan auto generate
    // Untuk menentukan nilai awal dari id adalah 0
    @PrimaryKey(autoGenerate = true) var id = 0

}