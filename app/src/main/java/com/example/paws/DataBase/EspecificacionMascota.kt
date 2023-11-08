package com.example.paws.DataBase

import com.example.paws.Interfaces.Especificacion
import com.google.firebase.firestore.FirebaseFirestore

class EspecificacionMascota :Especificacion
{
    override var edad: Int

    override var raza: String

    override var personalidad: String

    override var concentrado: String

    constructor(edad:Int,raza:String,personalidad:String,concentrado:String){
        this.edad=edad
        this.raza=raza
        this.personalidad=personalidad
        this.concentrado=concentrado
    }

    fun mapEspecificaciones(): Map<String, Any> {
        return mapOf(
            "edad" to this.edad,
            "raza" to this.raza,
            "personalidad" to this.personalidad,
            "concentrado" to this.concentrado
        )
    }
}