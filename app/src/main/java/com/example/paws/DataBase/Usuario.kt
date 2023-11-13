package com.example.paws.DataBase

import android.provider.ContactsContract.CommonDataKinds.Email
import com.example.paws.Interfaces.Mascota
import com.example.paws.Interfaces.User
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import java.io.Serializable

class Usuario : User {
    override var userName: String
    override var email: String
    override var contrasenia: String
    override var mascota: Mascota

    constructor(userName:String, email: String, contrasenia:String,mascota:Pet){
        this.userName=userName
        this.email=email
        this.contrasenia=contrasenia
        this.mascota=mascota
    }

    //TODO: Aca va el envio de los tados a la base de datos

    private val db = Firebase.firestore


    fun mapUsuario(): Map<String, Serializable> {
        return  mapOf(
            "userName" to this.userName,
            "password" to this.contrasenia,
            "mascota" to arrayListOf<Mascota>(mascota)
        )
    }

}


