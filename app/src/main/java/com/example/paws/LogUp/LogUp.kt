 package com.example.paws.LogUp

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.paws.DataBase.EspecificacionMascota
import com.example.paws.DataBase.Pet
import com.example.paws.DataBase.Usuario
import com.example.paws.Home.Home
import com.example.paws.R
import com.google.firebase.auth.FirebaseAuth

 class LogUp : AppCompatActivity() {

    //TODO: instancia variables de tipo users,mascota,enumTipoMascota, especificaciones
    lateinit var user: Usuario
    lateinit var mascota: Pet
    lateinit var enumTipoMascota:Array<String>
    lateinit var especificaciones: EspecificacionMascota

    // variables que capturan los datos del layout -- Usuario
    lateinit var edtNombreUsuario:EditText
    lateinit var editTextCorreo: EditText
    lateinit var editTextContrasenia: EditText
    lateinit var editTextReContrasenia: EditText

    // variables que capturan los datos del layout -- Mascota
    // el spiner
    lateinit var spinnerTipoMascota: Spinner
    lateinit var edtNombreMascota:EditText

    //TODO:mirar como se captura una imagen
    lateinit var fotoMascota:ImageButton
    lateinit var buttonEspecificaciones: Button

    lateinit var btnLogUp:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_up)

        //Inicializar capturar datos -- Usuario
        this.editTextCorreo=findViewById(R.id.edtEmail)
        this.editTextContrasenia=findViewById(R.id.edtContrasenia)
        this.editTextReContrasenia=findViewById(R.id.edtReContrasenia)

        //BTN CREAR
        this.btnLogUp=findViewById(R.id.btnCrearCuenta)


        btnLogUp.setOnClickListener {
            logUp()
        }

    }

    private fun logUp() {

        var emailUsuario:String=editTextCorreo.text.toString()
        var contrasenia:String=editTextContrasenia.text.toString()
        var reContrasenia:String=editTextReContrasenia.text.toString()

        if (emailUsuario.length==0&&contrasenia!=reContrasenia){
            Toast.makeText(this,"Datos del usuario incorrectos",Toast.LENGTH_LONG).show()
        }
        else{
            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(emailUsuario,contrasenia)
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        showHome(it.result?.user?.email?:"")
                    }
                    else{
                        showAlert()
                    }
                }
            }
        }

     private fun showHome(email:String) {
         val homeIntent=Intent(this,Home::class.java).apply {
             putExtra("email",email)
         }
         startActivity(homeIntent)
     }

     private fun showAlert() {
         val builder=AlertDialog.Builder(this)
         builder.setTitle("Error")
         builder.setMessage("Se ha producido un error autenticando el usuario")
         builder.setPositiveButton("Aceptar",null)
         val dialog:AlertDialog=builder.create()
         dialog.show()
     }

 }