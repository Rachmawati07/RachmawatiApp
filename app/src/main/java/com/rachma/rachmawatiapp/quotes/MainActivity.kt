package com.rachma.rachmawatiapp.quotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.rachma.rachmawatiapp.R

// Untuk mendeklarasikan class yang bernama MainActivity
class MainActivity : AppCompatActivity() {
    // Untuk menginisialisasi variable navController
    private lateinit var navController: NavController

    // Untuk memanggil kelas super onCreate dalam pembuatan activity ini
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Untuk memanggil navigation
        navController = Navigation.findNavController(this, R.id.mainFragment)
    }

    // Untuk memanggil method onSupportNavigateUp
    // Untuk mengembalikan navController
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}


