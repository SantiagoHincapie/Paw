 package com.example.paws.LogUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AbsSpinner
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast
import com.example.paws.DataBase.EspecificacionMascota
import com.example.paws.DataBase.Pet
import com.example.paws.DataBase.Usuario
import com.example.paws.Interfaces.Mascota
import com.example.paws.Interfaces.TipoMascota
import com.example.paws.Interfaces.User
import com.example.paws.R

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
        this.edtNombreUsuario=findViewById(R.id.edtLogUpUserName)
        this.editTextCorreo=findViewById(R.id.edtEmail)
        this.editTextContrasenia=findViewById(R.id.edtContrasenia)
        this.editTextReContrasenia=findViewById(R.id.edtReContrasenia)

        //TODO:Inicializar captura datos -- Mascota
        //Capturar dato del spinner
        this.enumTipoMascota=TipoMascota.values().map { it.v }.toTypedArray()
        val adapter=ArrayAdapter(this,android.R.layout.simple_spinner_item,enumTipoMascota)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        this.spinnerTipoMascota=findViewById(R.id.SpinnerTipoMascota)
        this.spinnerTipoMascota.adapter=adapter

        //TODO: capturar la foto
        this.edtNombreMascota=findViewById(R.id.edtNombreMascota)
        this.fotoMascota=findViewById(R.id.imageButtonFotoMascota)

        // Realizar las validaciones correspondientes para las especificaciones
        this.buttonEspecificaciones=findViewById(R.id.BtnEspecificaciones)

        //BTN CREAR
        this.btnLogUp=findViewById(R.id.btnCrearCuenta)

        //accion de los botones y del spinner


        btnLogUp.setOnClickListener {
            logUp()
        }
        buttonEspecificaciones.setOnClickListener {
            irAEspecificaciones()
        }

    }

    private fun irAEspecificaciones() {

        var nombreUsuario:String=edtNombreUsuario.text.toString()
        var correoUsuario:String=editTextCorreo.text.toString()
        var contrasenia:String=editTextContrasenia.text.toString()
        var reContrasenia:String=editTextReContrasenia.text.toString()
        var tipoMascota:String
        var nombreMascota=edtNombreMascota.text.toString()
        tipoMascota=spinnerTipoMascota.selectedItem.toString()

        // Validaciones

        if (nombreUsuario.length==0&&correoUsuario.length==0&&contrasenia!=reContrasenia){
            Toast.makeText(this,"Datos del usuario incorrectos",Toast.LENGTH_LONG).show()
        }
        else{
            if (nombreMascota.length==0&&tipoMascota.length==0){
                Toast.makeText(this,"Datos de la la mascota incompletos",Toast.LENGTH_LONG).show()
            }
            else{
                enviarData(nombreUsuario,correoUsuario,contrasenia,tipoMascota,nombreMascota,LogUpEspecificaciones::class.java)
            }
        }
    }

    private fun logUp() {
        var nombreUsuario:String=edtNombreUsuario.text.toString()
        var correoUsuario:String=editTextCorreo.text.toString()
        var contrasenia:String=editTextContrasenia.text.toString()
        var reContrasenia:String=editTextReContrasenia.text.toString()
        var tipoMascota:String
        var nombreMascota=edtNombreMascota.text.toString()
        tipoMascota=spinnerTipoMascota.selectedItem.toString()

        if (nombreUsuario.length==0&&correoUsuario.length==0&&contrasenia!=reContrasenia){
            Toast.makeText(this,"Datos del usuario incorrectos",Toast.LENGTH_LONG).show()
        }
        else{
            if (nombreMascota.length==0&&tipoMascota.length==0){
                Toast.makeText(this,"Datos de la la mascota incompletos",Toast.LENGTH_LONG).show()
            }
            else{
                //Aca se crea el usuario
                //TODO: hacer una clase que controla las acciones de la base de datos , esta clase tiene un array de mascota

                //TODO: hacer una clase que controla las acciones de la base de datos de mascota, esta clase tiene un array de historial vacuna
                //TODO: hacer una clase que controla las acciones de la base de datos de especificaciones
                //TODO: hacer una clase que controla las acciones en la base de datos de historial vacuna
                //TODO: hacer una clase que controla las acciones en la base de datos de vacuna
            }
        }
    }

    private fun enviarData
                (nombreUsuario: String,
                 correoUsuario:String,
                 contrasenia:String,
                 tipoMascota: String,
                 nombreMascota:String,
                 clase:Class<*>)
    {
        val intent:Intent=Intent(this,clase).apply {
            putExtra("nombreUsuario",nombreUsuario)
            putExtra("correoUsuario",correoUsuario)
            putExtra("contrasenia",contrasenia)
            putExtra("tipoMascota",tipoMascota)
            putExtra("nombreMascota",nombreMascota)
            //TODO:Falta capturar la imagen de la mascota
            //putExtra("tipoMascota",tipoMascota)
        }
        startActivity(intent)
    }

    private fun dataUsuario(){
        //TODO:Capturar los datos del usuario

    }

    private fun dataMascota(){
        //TODO:Capturar los datos de la mascota

    }


}