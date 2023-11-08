package com.example.paws.DataBase

import com.example.paws.Interfaces.Especificacion
import com.example.paws.Interfaces.HistorialVacuna
import com.example.paws.Interfaces.Mascota
import com.example.paws.Interfaces.TipoMascota
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class Pet: Mascota {

    override var tipoMascota: String
    override var especificaciones: Especificacion
    override var nombre: String
    override var foto: String
    override var historialVacuna: List<HistorialVacuna>
    constructor(
        tipoMascota: String,
        especificaciones: Especificacion,
        nombre: String,
        foto: String,
        historialVacuna: List<HistorialVacuna>
    ) {
        this.tipoMascota=tipoMascota
        this.especificaciones=especificaciones
        this.nombre=nombre
        this.foto=foto
        this.historialVacuna=historialVacuna
    }

    fun mapMascota(): HashMap<String, Any> {
        return hashMapOf(
            "tipoMascota" to this.tipoMascota,
            "especificaciones" to this.especificaciones,
            "nombrePet" to this.nombre,
            "foto" to "si",
            "historialVacuna" to this.historialVacuna
        )
    }
}