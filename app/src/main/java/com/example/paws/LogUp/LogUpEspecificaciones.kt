 package com.example.paws.LogUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.paws.Home.Home
import com.example.paws.R
import com.google.firebase.firestore.FirebaseFirestore

 class LogUpEspecificaciones : AppCompatActivity() {

    lateinit var edtPetAge:EditText
    lateinit var edtRace:EditText
    lateinit var edtPersonality:EditText
    lateinit var edtConcentrado:EditText
    lateinit var btnAddPet:Button
    lateinit var emailUser:String

    private val db= FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_up_especificaciones)

        edtPetAge=findViewById(R.id.edtEdadMascota)
        edtRace=findViewById(R.id.edtRazaMascota)
        edtPersonality=findViewById(R.id.edtPersonalidadMascota)
        edtConcentrado=findViewById(R.id.edtConcentradoMascota)
        btnAddPet=findViewById(R.id.btnAddPetEspecifications)

        emailUser=intent.getStringExtra("emailUser").toString()

        btnAddPet.setOnClickListener {
            newPet()
        }
    }

    private fun newPet() {
        var petAge:Int?=edtPetAge.text.toString().toInt()
        var race=edtRace.text.toString()
        var personality=edtPersonality.text.toString()
        var concentrado=edtConcentrado.text.toString()

        var petName =intent.getStringExtra("petName").toString()
        var petType=intent.getStringExtra("petType").toString()

        Log.i("TAG_INFO", "Entro a newPet method")

        if (petAge==null||race.length==0||personality.length==0||concentrado.length==0)
        {
            Toast.makeText(this, "Estos datos se podran ingresar despues", Toast.LENGTH_LONG).show()
            addPet(petName,petType,petAge,race,personality,concentrado)
        }
        else{
            addPet(petName,petType,petAge,race,personality,concentrado)
        }

    }

    private fun addPet(petName:String,petType:String,petAge:Int?,petRace:String?,petPersonality:String?,concentrado:String?) {
        //TODO: Crear la mascota en firestore
        db.collection("users").document(emailUser)
            .collection("pets").document(petName).set(
                hashMapOf(
                    "petType" to petType,
                    "petAge" to petAge,
                    "petRace" to petRace,
                    "petPersonality" to petPersonality,
                    "concentrado" to concentrado
                )
            )

        val intent= Intent(this,Home::class.java)
        startActivity(intent)
        finish()
    }
}