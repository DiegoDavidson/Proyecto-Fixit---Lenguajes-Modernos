package com.example.fixit

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.fixit.databinding.RegistroClienteBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class RegisterCliente : AppCompatActivity() {
    //auth firebase
    private lateinit var auth: FirebaseAuth
    private lateinit var btnregistro : Button
    private lateinit var CorreoEditText: EditText
    private lateinit var contra2ClienteEditText: EditText
    private lateinit var telefonoEditText: EditText
    private lateinit var contraClienteEditText: EditText
    private lateinit var nombreClienteEditText: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registro_cliente)

        auth= Firebase.auth

        //set campos por id
        nombreClienteEditText = findViewById<EditText>(R.id.ETnombre_cliente)
        btnregistro = findViewById<Button>(R.id.btnregistro_cliente)
        CorreoEditText = findViewById<EditText>(R.id.ETcorreo_cliente)
        telefonoEditText = findViewById<EditText>(R.id.ETtelefono_cliente)
        contraClienteEditText = findViewById<EditText>(R.id.ETcontra_cliente)
        contra2ClienteEditText = findViewById<EditText>(R.id.ETcontra2_cliente)

        btnregistro.setOnClickListener {
            //obtener datos registro
            val nombre: String = nombreClienteEditText.text.toString()
            val correo = CorreoEditText.text.toString()
            val pass = contraClienteEditText.text.toString()
            val pass2 = contra2ClienteEditText.text.toString()
            val telefono = telefonoEditText.text.toString()

            //validacion

            if(nombre.isEmpty()){
                CorreoEditText.error = "Ingrese su correo"
                return@setOnClickListener
            }
            if (correo.isEmpty()){
                CorreoEditText.error = "ingrese su correo"
                return@setOnClickListener
            }
            if (pass.isEmpty()){
                contraClienteEditText.error = "ingrese su contraseña"
                return@setOnClickListener
            }
            if (pass2.isEmpty()){
                contra2ClienteEditText.error = "ingrese nuevamente su contraseña"
                return@setOnClickListener
            }
            if (telefono.isEmpty()){
                telefonoEditText.error = "ingrese su telefono"
            }

            if(pass==pass2){
                registrarNuevoUsuario(correo,pass)
            }else{
                Toast.makeText(this,"las contraseñas no coinciden", Toast.LENGTH_LONG).show()
            }


        }



    }

    private fun registrarNuevoUsuario(nombre: String, pass: String) {
        auth.createUserWithEmailAndPassword(nombre,pass)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(this,"usuario creado completamente", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this,"error", Toast.LENGTH_LONG).show()
                }
            }

    }


    fun ReturnRegisterUser(view: View) {
        val intent = Intent(this, SelectUser::class.java)
        startActivity(intent)
    }

}

