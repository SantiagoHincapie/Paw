package com.example.paws.DataBase

import android.provider.ContactsContract.CommonDataKinds.Email
import com.example.paws.Interfaces.Mascota
import com.example.paws.Interfaces.User
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import java.io.Serializable

class Usuario {
    private val db= FirebaseFirestore.getInstance()
    private var email:String

    constructor(email:String){
        this.email=email
    }

    fun getUser(): DocumentReference {
        return db.collection("users").document(email)
    }

}


