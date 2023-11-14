package com.example.paws.Home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import com.example.paws.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.FirebaseFirestore

class HomePerfilMascota : AppCompatActivity() {

    //Esto puede ir en una clase aparte
    private val db= FirebaseFirestore.getInstance()

    lateinit var userEmail:String
    lateinit var petId:String

    lateinit var txvPetName:TextView
    lateinit var btnHistorialClinico:Button
    lateinit var btnToJorney:Button

    //TODO:Despues miro
    lateinit var btnVavigationV:BottomNavigationView
    lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_perfil_mascota)

        userEmail=intent.getStringExtra("userEmail").toString()
        petId=intent.getStringExtra("petName").toString()
        txvPetName=findViewById(R.id.textViewNombreMascota)
        btnHistorialClinico=findViewById(R.id.buttonHistorialClinico)
        btnToJorney=findViewById(R.id.buttonJorney)

        txvPetName.text=petId

        btnHistorialClinico.setOnClickListener {
            toHistory("Historial Clinico")
        }
        btnToJorney.setOnClickListener {
            toHistory("Nos vamos de viaje")
        }
    }

    private fun toHistory(a:String) {
        val intent=Intent(this,HomeHistorialClinicoAndJorneys::class.java).apply {
            putExtra("userEmail",userEmail)
            putExtra("petName",petId)
            putExtra("a",a)
        }
        startActivity(intent)
    }

    private fun navigation() {

    }
}