package com.example.paws.LogIn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.paws.LogUp.LogUp
import com.example.paws.R

class LogIn : AppCompatActivity() {

    lateinit var btnLogIn:Button
    lateinit var edtUsuario:EditText
    lateinit var edtContrasenia:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

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

        }


    }

    fun irARegistro(view:View)
    {
        val intent:Intent=Intent(this,LogUp::class.java)
        startActivity(intent)
    }
}