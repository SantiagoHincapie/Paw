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
    private var documentList=ArrayList<String>()


    lateinit var userEmail:String
    lateinit var fbNewPet:FloatingActionButton
    lateinit var lvPets:ListView

    lateinit var txvCerrarSesion:TextView
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        this.userEmail= intent.getStringExtra("email").toString()
        txvCerrarSesion=findViewById(R.id.textViewCerrarSesion)
        fbNewPet=findViewById(R.id.btnAddPet)
        lvPets=findViewById(R.id.listViewMascotas)

        txvCerrarSesion.setOnClickListener {
            cerrarSesion()
        }

        fbNewPet.setOnClickListener {
            newPet()
        }
    }

    private fun myPets(petsRef: CollectionReference) {
        documentList.clear()
        //Aca se muestran las moscotas que se tiene
        petsRef.get()
            .addOnCompleteListener(OnCompleteListener <QuerySnapshot> { task ->
                if (task.isSuccessful){


                    for (doc in task.result!!){
                        val docId=doc.id
                        this.documentList.add(docId)
                    }

                    val adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,documentList)
                    this.lvPets.adapter=adapter
                }
            })
        lvPets.setOnItemClickListener { parent, view, position, id ->
            val intent:Intent=Intent(this,HomePerfilMascota::class.java).apply {
                putExtra("userEmail",userEmail)
                putExtra("petName",documentList[position])
            }
            startActivity(intent)
            finish()
        }
    }

    private fun newPet() {
        var intent:Intent=Intent(this,MascotaForm::class.java)
        startActivity(intent)
        //finish()
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
        Log.i("TAG_INFO", "En el estar")

    }

    override fun onResume() {
        super.onResume()
        Log.i("TAG_INFO", "En el resume")

        val petsRef=db.collection("users").document(userEmail)
            .collection("pets")
        myPets(petsRef)

    }

    override fun onPause() {
        super.onPause()
        Log.i("TAG_INFO", "En el pause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("TAG_INFO", "En el stop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("TAG_INFO", "En el destroy")
    }


}