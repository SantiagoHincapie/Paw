package com.example.paws.Home

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.paws.R
import com.google.firebase.auth.FirebaseAuth

class Home : AppCompatActivity() {

    lateinit var userEmail:String

    lateinit var txvCerrarSesion:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        userEmail= intent.getStringExtra("email").toString()
        txvCerrarSesion=findViewById(R.id.textViewCerrarSesion)

        txvCerrarSesion.setOnClickListener {
            cerrarSesion()
        }

    }

    private fun cerrarSesion(){
        showAlert()
        FirebaseAuth.getInstance().signOut()

    }
    private fun showAlert() {
        val builder= AlertDialog.Builder(this)
        builder.setTitle("Cerrando sesion")
        builder.setMessage("¿Seguro que desea cerrar sesion?")
        builder.setPositiveButton("Aceptar",DialogInterface.OnClickListener { dialog, i -> onBackPressed() })
        builder.setNegativeButton("Cancelar",null)
        val dialog: AlertDialog =builder.create()
        dialog.show()
    }
}