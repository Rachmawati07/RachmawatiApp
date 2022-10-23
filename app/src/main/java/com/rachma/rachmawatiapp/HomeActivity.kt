package com.rachma.rachmawatiapp

import android.os.Bundle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.rachma.rachmawatiapp.databinding.ActivityHomeBinding
import com.rachma.rachmawatiapp.quotes.MainActivity

// Untuk mendeklarasikan class yang bernama HomeActivity
class HomeActivity : AppCompatActivity() {

    // Untuk menginisialisasi variable binding
    private lateinit var binding: ActivityHomeBinding

    // Untuk memanggil kelas super onCreate dalam pembuatan activity ini
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Untuk memanggil ActivityHomeBinding dengan menggunakan binding
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Untuk memberikan fungsi klik listener pada btnLastread agar ketika diklik bisa diarahkan pada fungsi list
        binding.btnLastread.setOnClickListener { list() }

        // Untuk memberikan fungsi klik listener pada btnIslami agar ketika diklik bisa diarahkan pada fungsi quotes
        binding.btnIslami.setOnClickListener { quotes() }
    }

    // Untuk mendeklarasikan fungsi yang bernama list
    private fun list(){

        // Untuk mendeklarasikan variabel berupa intentList dari kelas Intent
        // Untuk mendeklarasikan parameter dari kelas aktif yang digunakan sekarang(HomeActivity menuju ke kelas ListActivity)
        val intentList = Intent(this, ListActivity::class.java)

        // Untuk menjalankan activity melalui intent yang telah dideklarasikan
        startActivity(intentList)
    }

    // Untuk mendeklarasikan fungsi yang bernama quotes
    private fun quotes(){

        // Untuk mendeklarasikan variabel berupa intentQuotes dari kelas Intent
        // Untuk mendeklarasikan parameter dari kelas aktif yang digunakan sekarang(HomeActivity menuju ke kelas MainActivity)
        val intentQuotes = Intent(this, MainActivity::class.java)

        // Untuk menjalankan activity melalui intent yang telah dideklarasikan
        startActivity(intentQuotes)
    }
}