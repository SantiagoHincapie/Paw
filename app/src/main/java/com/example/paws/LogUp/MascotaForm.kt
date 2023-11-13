package com.example.paws.LogUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.paws.Interfaces.TipoMascota
import com.example.paws.R
import com.google.firebase.firestore.FirebaseFirestore

class MascotaForm : AppCompatActivity() {

    lateinit var edtPetName:EditText
    lateinit var spinnerPetType:Spinner
    lateinit var btnEspecification:Button
    lateinit var btnAddPet:Button
    lateinit var emailUser:String

    private val db=FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mascota)

        edtPetName=findViewById(R.id.edtNombreMascota)
        spinnerPetType=findViewById(R.id.SpinnerTipoMascota)
        btnEspecification=findViewById(R.id.btnAddEspecificaciones)
        btnAddPet=findViewById(R.id.btnAddMascota)

        emailUser= intent.getStringExtra("email").toString()

        val enumValues=TipoMascota.values()
        val enumOptions=enumValues.map { it.v }.toTypedArray()
        val adapter=ArrayAdapter(this,android.R.layout.simple_spinner_item,enumOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerPetType.adapter=adapter


        btnEspecification.setOnClickListener {
            especificaciones()
        }
        btnAddPet.setOnClickListener {
            newPet()
        }
    }

    private fun especificaciones() {
        var petName:String=edtPetName.text.toString()
        var petType=spinnerPetType.selectedItem.toString()
        val intent:Intent=Intent(this,LogUpEspecificaciones::class.java).apply {
            putExtra("petName",petName)
            putExtra("petType",petType)
            //TODO:falta la foto
        }
        startActivity(intent)
    }
    private fun newPet() {

        //TODO:Capturar los datos
        var petName:String=edtPetName.text.toString()
        var petType=spinnerPetType.selectedItem.toString()

        addPet(petName,petType,null,null,null,null)
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
    }

}