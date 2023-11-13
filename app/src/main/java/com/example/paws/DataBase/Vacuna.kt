package com.example.paws.DataBase

import com.example.paws.Interfaces.Vacuna

class Vacuna
     :Vacuna
{
    override var nombre: String
    override var descripcion: String
    constructor(nombre: String, descripcion: String) {
        this.nombre = nombre
        this.descripcion = descripcion
    }

    //Convercion de los datos entrantes a un tipo mapa
    fun toMapVacuna(): Map<String, String> {
        return mapOf(
            "vacuna" to this.nombre,
            "descripcion" to this.descripcion
        )
    }
}