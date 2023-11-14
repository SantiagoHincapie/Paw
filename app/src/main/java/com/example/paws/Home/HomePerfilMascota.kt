package com.example.paws.Home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.paws.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomePerfilMascota : AppCompatActivity() {

    lateinit var btnVavigationV:BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_perfil_mascota)

        btnVavigationV=findViewById(R.id.btnNavigationBar)


    }

    private fun navigation() {

    }
}