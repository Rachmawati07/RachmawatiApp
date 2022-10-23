package com.rachma.rachmawatiapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rachma.rachmawatiapp.note.Note

// Untuk mendeklarasikan class yang bernama NoteRVAdapter
class NoteRVAdapter(

    // Untuk mendeklarasikan variabel berupa content, noteClickDeleteInterface dan noteClickInterface
    val context: Context,
    val noteClickDeleteInterface: NoteClickDeleteInterface,
    val noteClickInterface: NoteClickInterface
) :
    RecyclerView.Adapter<NoteRVAdapter.ViewHolder>() {

    // Untuk mendeklarasikan variabel yang bernama allNotes
    private val allNotes = ArrayList<Note>()

    // Untuk mendeklarasikan sebuah class yang bernama ViewHolder
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // Untuk mendeklarasikan variabel yang bernama noteTV, dateTV dan deleteTV
        val noteTV = itemView.findViewById<TextView>(R.id.idTVNote)
        val dateTV = itemView.findViewById<TextView>(R.id.idTVDate)
        val deleteIV = itemView.findViewById<ImageView>(R.id.idIVDelete)
    }

    // Untuk memanggil kelas super onCreate dalam pembuatan activity ini
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        // Untuk mendeklarasikan variabel yang bernama itemView
        // untuk menginflate file layout untuk setiap item recyclerview
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.note_rv_item,
            parent, false
        )
        return ViewHolder(itemView)
    }

    // Untuk memanggil kelas onBindViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // Untuk mengatur data ke item recycler view
        holder.noteTV.setText(allNotes.get(position).noteTitle)
        holder.dateTV.setText("Last Updated : "+allNotes.get(position).timeStamp)

        // Untuk menambahkan klik listener pada icon delete pada image view
        holder.deleteIV.setOnClickListener {

            // Untuk memanggil note click delete interface dan meletakkan posisinya kesana
            noteClickDeleteInterface.onDeleteIconClick(allNotes.get(position))
        }

        // Untuk menambahkan klik listener pada item recycler view
        holder.itemView.setOnClickListener {

            // Untuk memanggil note click interface dan meletakkan posisinya kesana
            noteClickInterface.onNoteClick(allNotes.get(position))
        }
    }

    // Untuk memanggil fungsi getItemCount
    override fun getItemCount(): Int {
        // Untuk mengembalikan ukuran list
        return allNotes.size
    }

    // Untuk mendeklarasikan fungsi yang bernama
    // Untuk mengupdate data pada list
    fun updateList(newList: List<Note>) {

        // Untuk menghapus daftar array
        allNotes.clear()

        // Untuk menambahkan list baru pada semua daftar list
        allNotes.addAll(newList)

        // Untuk memanggil metode notifyDataSetChanged untuk memberi tahu adaptor
        notifyDataSetChanged()
    }

}

// Untuk mendeklarasikan interface yang bernama NoteClickDeleteInterface
interface NoteClickDeleteInterface {

    // Untuk membuat metode untuk klik action tampilan gambar yang dihapus.
    fun onDeleteIconClick(note: Note)
}

// Untuk mendeklarasikan interface yang bernama NoteClickInterface
interface NoteClickInterface {
    // Untuk membuat metode untuk klik action pada recycler view untuk memperbaruinya
    fun onNoteClick(note: Note)
}