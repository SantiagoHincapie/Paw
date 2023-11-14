package com.example.paws.Home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.paws.R

class HomeHistorialClinicoAndJorneys : AppCompatActivity() {

    lateinit var userEmail:String
    lateinit var petName:String
    lateinit var titulo:String

    lateinit var txvTituloPagina:TextView
    lateinit var txvPetName:TextView
    lateinit var txvFecha:TextView
    lateinit var txvEdad:TextView
    lateinit var txvRaza:TextView
    lateinit var txvPersonalidad:TextView
    lateinit var txvConcentrado:TextView
    lateinit var btnUltimaVisita:Button
    lateinit var btnVacunas:Button
    lateinit var btnHistorial:Button

    //TODO:el button de agregar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_historial_clinico)

        userEmail=intent.getStringExtra("userEmail").toString()
        petName=intent.getStringExtra("petName").toString()
        titulo=intent.getStringExtra("a").toString()

        txvTituloPagina=findViewById(R.id.textViewTitulo)
        txvPetName=findViewById(R.id.textViewPetName)
        txvFecha=findViewById(R.id.textViewFecha)
        txvEdad=findViewById(R.id.txvEdad)
        txvRaza=findViewById(R.id.txvRaza)
        txvPersonalidad=findViewById(R.id.txvPersonalidad)
        txvPersonalidad=findViewById(R.id.txvConcentrado)

        especificaciones()

        btnUltimaVisita.setOnClickListener {
            visitas()
        }
        btnVacunas.setOnClickListener {
            vacunas()
        }
        btnHistorial.setOnClickListener {
            historial()
        }

    }

    private fun especificaciones() {
        //TODO: aca va la peticion get que se realiza a las especificaciones de la mascota
    }

    private fun historial() {
        val intent=Intent(this,HomeHistorialCompleto::class.java).apply {
            putExtra("userEmail",userEmail)
            putExtra("petName",petName)
        }
        startActivity(intent)
    }

    private fun vacunas() {
        val intent=Intent(this,HomeHistorialVacunas::class.java).apply {
            putExtra("userEmail",userEmail)
            putExtra("petName",petName)
        }
        startActivity(intent)
    }

    private fun visitas() {
        val intent=Intent(this,HomeHistorialVisitas::class.java).apply {
            putExtra("userEmail",userEmail)
            putExtra("petName",petName)
        }
        startActivity(intent)
    }
}