package com.rachma.rachmawatiapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.rachma.rachmawatiapp.quotes.ListFragment
import com.rachma.rachmawatiapp.R
import com.rachma.rachmawatiapp.data.Note
import com.rachma.rachmawatiapp.databinding.NoteRowBinding

// Untuk mendeklarasikan class yang bernama NoteAdapter
class NoteAdapter(private val listFragment: ListFragment): RecyclerView.Adapter<NoteAdapter.ItemViewHolder>() {
    // Untuk mendeklarasikan variabel yang bernama notes
    private var notes = emptyList<Note>()

    // Untuk mendeklarasikan class yang bernama ItemViewHolder
    class ItemViewHolder(val binding: NoteRowBinding): RecyclerView.ViewHolder(binding.root)

    // Untuk memanggil fungsi yang bernama onCreateViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.ItemViewHolder {
        // Untuk mengembalikan ItemViewHolder
        return ItemViewHolder(
            NoteRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    // Untuk memanggil fungsi onBindViewHolder
    // Untuk mendeklarasikan variabel yang bernama note
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val note = notes[position]

        // Untuk menjalankan binding
        holder.binding.apply {
            tvNote.text = note.noteText
            ibEditNote.setOnClickListener {

                // Menggunakan Shared Preferences untuk meneruskan NoteId dari NoteAdapter ke Update Fragment
                with(listFragment.sharedPreferences.edit()) {
                    putString("NoteId", note.id)
                    apply()
                }
                listFragment.findNavController().navigate(R.id.action_list_to_update)
            }
            // Untuk memberikan klik listener pada ibDeleteNote
            // Untuk menghapus data
            ibDeleteNote.setOnClickListener {
                listFragment.listViewModel.deleteNote(note.id)
            }
        }
    }

    // Untuk memanggil method getItemCount
    override fun getItemCount() = notes.size

    // Untuk mendeklarasikan fungsi yang bernama update
    // Untuk mengupdate data
    fun update(notes: List<Note>){
        this.notes = notes
        notifyDataSetChanged()
    }
}
