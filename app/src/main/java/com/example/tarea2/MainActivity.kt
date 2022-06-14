package com.example.tarea2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.tarea2.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseApp.initializeApp(this)
        auth = Firebase.auth
        binding.btSignon.setOnClickListener{
            registro()
        }
        binding.btSigin.setOnClickListener {
            login()
        }
    }

    //fun: function
    private fun registro() {
        val email = binding.inputEmail.text.toString()
        val password = binding.inputPass.text.toString()

        if(email.isNotBlank() && password.isNotBlank()) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) {task ->
                    if (task.isSuccessful) {
                        Log.d("Creando usuario", "Registrado")
                        val user = auth.currentUser
                        cambioPantalla(user)
                    } else {
                        Log.d("Creando usuario", "Falló")
                        Toast.makeText(baseContext, "Falló", Toast.LENGTH_LONG).show()
                        cambioPantalla(null)
                    }
                }
        } else {
            Toast.makeText(baseContext, "Ingrese datos de usuario validos", Toast.LENGTH_LONG).show()
        }

    }

    private fun login() {
        val email = binding.inputEmail.text.toString()
        val password = binding.inputPass.text.toString()

        if(email.isNotBlank() && password.isNotBlank()) {
            try {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) {task ->
                        if (task.isSuccessful) {
                            Log.d("Creando usuario", "Registrado")
                            val user = auth.currentUser
                            cambioPantalla(user)
                        } else {
                            Log.d("Creando usuario", "Falló")
                            Toast.makeText(baseContext, "Falló", Toast.LENGTH_LONG).show()
                            cambioPantalla(null)
                        }
                    }
            }catch (err: Error) {
                Toast.makeText(baseContext, "Falló", Toast.LENGTH_LONG).show()
            }

        } else {
            Toast.makeText(baseContext, "Ingrese datos de usuario validos", Toast.LENGTH_LONG).show()
        }
    }

    //Mostrar actividad, es decir cambiar de pantalla
    private fun cambioPantalla(usuario: FirebaseUser?) {
        if (usuario != null) {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    //En caso que el usuario ya este logeado no se pidan credenciales de nuevo
    public override fun onStart() {
        super.onStart()
        val user = auth.currentUser
        cambioPantalla(user)
    }
}