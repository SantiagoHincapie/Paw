package com.example.paws.LogIn

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.paws.Home.Home
import com.example.paws.LogUp.LogUp
import com.example.paws.R
import com.google.firebase.auth.FirebaseAuth

class LogIn : AppCompatActivity() {

    lateinit var btnLogIn: Button
    lateinit var txvLogUp:TextView
    lateinit var edtUsuario: EditText
    lateinit var edtContrasenia: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)


        btnLogIn = this.findViewById(R.id.buttonLogIn)
        edtUsuario = this.findViewById(R.id.editTextLogIn)
        edtContrasenia = this.findViewById(R.id.editTextContrasenia)
        txvLogUp=findViewById(R.id.textViewRegistro)

        btnLogIn.setOnClickListener {
            irAHome()
        }

        txvLogUp.setOnClickListener {
            irARegistro()
        }
    }

    fun irAHome() {
        var usuario: String = this.edtUsuario.text.toString()
        var contrsenia: String = this.edtContrasenia.text.toString()


        if (usuario.length == 0 && contrsenia.length == 0) {
            Toast.makeText(this, "Ingrese usuario/contraseña", Toast.LENGTH_LONG).show()
        } else {
            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(usuario, contrsenia)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        showHome(it.result?.user?.email ?: "")
                    } else {
                        showAlert()
                    }
                }
        }
    }
    private fun showHome(email:String) {
        val homeIntent= Intent(this, Home::class.java).apply {
            putExtra("email",email)
        }
        startActivity(homeIntent)
    }
    private fun showAlert() {
        val builder= AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando el usuario")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog =builder.create()
        dialog.show()
    }
    fun irARegistro(){
        val intent=Intent(this,LogUp::class.java)
        startActivity(intent)
    }
}