package com.example.paws.LogUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.paws.Home.HomeHistorialClinicoAndJorneys
import com.example.paws.R
import com.google.firebase.firestore.FirebaseFirestore

class EditarEspecificaciones : AppCompatActivity() {
    lateinit var edtPetAge: EditText
    lateinit var edtRace: EditText
    lateinit var edtPersonality: EditText
    lateinit var edtConcentrado: EditText
    lateinit var btnEditPet: Button

    lateinit var emailUser:String
    lateinit var petName:String
    lateinit var a:String

    private val db= FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_especificaciones)

        emailUser=intent.getStringExtra("emailUser").toString()
        petName=intent.getStringExtra("petName").toString()
        a=intent.getStringExtra("a").toString()

        edtPetAge=findViewById(R.id.edtEdadMascota)
        edtRace=findViewById(R.id.edtRazaMascota)
        edtPersonality=findViewById(R.id.edtPersonalidadMascota)
        edtConcentrado=findViewById(R.id.edtConcentradoMascota)
        btnEditPet=findViewById(R.id.btnEditartEspecifications)


        btnEditPet.setOnClickListener {
            newEspecificaciones()
        }
    }

    private fun newEspecificaciones() {

        var concentrado:String=edtConcentrado.text.toString()
        var edad:Int=edtPetAge.text.toString().toInt()
        var raza:String=edtRace.text.toString()
        var personalidad:String=edtPersonality.text.toString()
        var petType:String
        val petRef=db.collection("users").document(emailUser)
            .collection("pets").document(petName)

        petRef.set(
            hashMapOf(
                "concentrado" to concentrado,
                "petAge" to edad,
                "petPersonality" to personalidad,
                "petRace" to raza
            )
        )
        val intent:Intent=Intent(this,HomeHistorialClinicoAndJorneys::class.java).apply {
            putExtra("userEmail",emailUser)
            putExtra("petName",petName)
            putExtra("a",a)
        }
        startActivity(intent)
    }
}