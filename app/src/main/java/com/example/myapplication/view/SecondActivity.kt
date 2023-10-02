package com.example.myapplication.view

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.model.Usuario
import com.example.myapplication.databinding.ActivitySecondBinding
import com.google.gson.Gson

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)

        setContentView(binding.root)
        val usuario = intent.extras?.getParcelable("USUARIO", Usuario::class.java)
        val prefs = this.getSharedPreferences("", Context.MODE_PRIVATE)

        val usuarioFromJson =
            Gson().fromJson(
                prefs.getString("USUARIO", "Deu ruim ao buscar o dado"),
                Usuario::class.java
            )

        val cursoNome = prefs.getString("USUARIO", "Deu ruim ao buscar o dado")

        binding.textViewNomedoUsuario.text = usuarioFromJson.dtNasc
    }
}