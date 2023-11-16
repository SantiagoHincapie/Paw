package com.example.paws.Home

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.paws.LogIn.LogIn
import com.example.paws.LogUp.MascotaForm
import com.example.paws.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlin.math.log

class Home : AppCompatActivity() {

    //Esta instancia puede ir en una clase
    private val db= FirebaseFirestore.getInstance()
    private var myPets=ArrayList<String>()


    lateinit var userEmail:String
    lateinit var fbNewPet:FloatingActionButton
    lateinit var lvPets:ListView

    lateinit var txvCerrarSesion:TextView
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        txvCerrarSesion=findViewById(R.id.textViewCerrarSesion)
        fbNewPet=findViewById(R.id.btnAddPet)
        lvPets=findViewById(R.id.listViewMascotas)

        this.userEmail= intent.getStringExtra("email").toString()

        //
        val petsRef=db.collection("users").document(userEmail)
            .collection("pets")
        myPets(petsRef)
        //
        txvCerrarSesion.setOnClickListener {
            cerrarSesion()
        }

        fbNewPet.setOnClickListener {
            newPet()
        }
    }

    private fun myPets(petsRef: CollectionReference) {

        Log.i("CORREO DEL USUARIO", "${userEmail}")

        petsRef.get().addOnSuccessListener { documentos->
            for (doc in documentos){
                myPets.add(doc.id)
            }
            Log.i("TAG_INFO", "${myPets}")
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, myPets)
            lvPets.adapter=adapter

            //click en la lista
            lvPets.setOnItemClickListener { parent, view, position, id ->
                    val intent:Intent=Intent(this,HomePerfilMascota::class.java).apply {
                        putExtra("email",userEmail)
                        putExtra("petName",myPets[position])
                    }
                    startActivity(intent)
                }
        }
    }

    private fun newPet() {
        var intent:Intent=Intent(this,MascotaForm::class.java).apply {
            putExtra("email",userEmail)
        }
        startActivity(intent)
    }

    private fun cerrarSesion(){
        showAlert()
    }
    private fun showAlert() {
        val builder= AlertDialog.Builder(this)
        builder.setTitle("Cerrando sesion")
        builder.setMessage("¿Seguro que desea cerrar sesion?")
        builder.setPositiveButton("Aceptar",DialogInterface.OnClickListener { dialog, i -> onBackPressed()
            FirebaseAuth.getInstance().signOut()})
        builder.setNegativeButton("Cancelar",null)
        val dialog: AlertDialog =builder.create()
        dialog.show()
    }

    override fun onStart() {
        super.onStart()
        this.userEmail= intent.getStringExtra("email").toString()
    }

    override fun onRestart() {
        super.onRestart()

        val petsRef=db.collection("users").document(userEmail)
            .collection("pets")
        myPets(petsRef)
    }

    override fun onResume() {
        super.onResume()
        Log.i("TAG_INFO", "En el resume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("TAG_INFO", "En el onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("TAG_INFO", "En el onStop")
    }


}