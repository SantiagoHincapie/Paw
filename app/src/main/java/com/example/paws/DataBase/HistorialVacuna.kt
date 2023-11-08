package com.example.paws.DataBase

import com.example.paws.Interfaces.HistorialVacuna
import com.example.paws.Interfaces.Vacuna
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Date

class HistorialVacuna:HistorialVacuna
{
    override lateinit var vacuna: Vacuna
    override lateinit var fecha: Date

    constructor(vacuna:Vacuna,fecha:Date){
        this.vacuna=vacuna
        this.fecha=fecha
    }

    fun historiaVacuna(): Map<String, Any> {
        return mapOf(
            "vacuna" to this.vacuna,
            "fecha" to this.fecha
        )
    }

}