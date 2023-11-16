package com.example.paws.Home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.paws.LogUp.EditarEspecificaciones
import com.example.paws.LogUp.LogUpEspecificaciones
import com.example.paws.R
import com.google.firebase.firestore.FirebaseFirestore

class HomeHistorialClinicoAndJorneys : AppCompatActivity() {

    private val db= FirebaseFirestore.getInstance()

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
    lateinit var txvEditar:TextView

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
        txvConcentrado=findViewById(R.id.txvConcentrado)
        txvEditar=findViewById(R.id.txvEditarEspecificaciones)

        btnUltimaVisita=findViewById(R.id.buttonUltimaVisita)
        btnVacunas=findViewById(R.id.buttonVacunas)
        btnHistorial=findViewById(R.id.buttonHistorialCompleto)

        Log.i("User email en el homeHistorial", "${userEmail}")

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
        txvEditar.setOnClickListener {
            editarEspecificaciones()
        }
    }

    private fun editarEspecificaciones() {

        val intent=Intent(this,EditarEspecificaciones::class.java).apply {
            putExtra("petName",petName)
            putExtra("emailUser",userEmail)
            putExtra("a",titulo)
        }
        startActivity(intent)
    }

    private fun especificaciones() {
        txvTituloPagina.text=titulo
        txvPetName.text=petName

        val petRef=db.collection("users").document(userEmail)
            .collection("pets").document(petName).get().addOnSuccessListener {
                txvEdad.text="Edad: "+ it.get("petAge").toString()
                txvRaza.text="Raza: "+it.get("petRace").toString()
                txvPersonalidad.text="Personalidad: "+it.get("petPersonality").toString()
                txvConcentrado.text="Concentrado: "+it.get("concentrado").toString()
                if (txvEdad.text=="0"||txvRaza.length()==0||txvPersonalidad.length()==0||txvConcentrado.length()==0){
                    Toast.makeText(this, "Especificaciones incompletas", Toast.LENGTH_LONG).show()
                }
            }

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