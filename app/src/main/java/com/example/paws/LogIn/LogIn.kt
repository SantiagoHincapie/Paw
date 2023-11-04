package com.example.paws.LogIn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.paws.DataBase.DataBaseConection
import com.example.paws.Home.Home
import com.example.paws.LogUp.LogUp
import com.example.paws.R
import com.google.firebase.firestore.FirebaseFirestore

class LogIn : AppCompatActivity() {

    lateinit var db:FirebaseFirestore

    lateinit var btnLogIn:Button
    lateinit var edtUsuario:EditText
    lateinit var edtContrasenia:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        db=DataBaseConection.instanciaDB()

        btnLogIn=this.findViewById(R.id.buttonLogIn)
        edtUsuario=this.findViewById(R.id.editTextLogIn)
        edtContrasenia=this.findViewById(R.id.editTextContrasenia)

        btnLogIn.setOnClickListener {
            irAHome()
        }
    }

    fun irAHome(){
        var usuario:String=this.edtUsuario.text.toString()
        var contrsenia:String=this.edtContrasenia.text.toString()

        if (usuario.length==0&&contrsenia.length==0)
        {
            Toast.makeText(this,"Ingrese usuario/contraseña",Toast.LENGTH_LONG).show()
        }
        else
        {
            if (usuario.length==0){
                Toast.makeText(this,"Ingrese usuario/email",Toast.LENGTH_LONG).show()
            }
            else{
                if (contrsenia.length==0){
                    Toast.makeText(this,"Ingrese contraseña",Toast.LENGTH_LONG).show()
                }
            }

            //TODO: realizar un GET a la base de datos con el dato email y contrasenia


            //TODO: si encuentra el usuario realizar un intent a Home, con todos los datos

            var intent:Intent=Intent(this,Home::class.java).apply {
                putExtra("paVer",1)
            }
            startActivity(intent)
        }


    }

    fun irARegistro(view:View)
    {
        val intent:Intent=Intent(this,LogUp::class.java)
        startActivity(intent)
    }
}