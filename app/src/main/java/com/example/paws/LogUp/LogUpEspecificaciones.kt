 package com.example.paws.LogUp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.paws.R

class LogUpEspecificaciones : AppCompatActivity() {

    lateinit var edtPetAge:EditText
    lateinit var edtRace:EditText
    lateinit var edtPersonality:EditText
    lateinit var edtConcentrado:EditText
    lateinit var btnAddPet:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_up_especificaciones)

        edtPetAge=findViewById(R.id.edtEdadMascota)
        edtRace=findViewById(R.id.edtRazaMascota)
        edtPersonality=findViewById(R.id.edtPersonalidadMascota)
        edtConcentrado=findViewById(R.id.edtConcentradoMascota)
        btnAddPet=findViewById(R.id.btnAddPetEspecifications)

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
    }
}