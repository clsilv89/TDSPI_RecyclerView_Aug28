package com.example.myapplication

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Usuario (
    val nome: String = "",
    val email: String = "",
    val matricula: String = "",
    val cpf: String = "",
    val genero: String = "",
    val fotoUrl: String = "",
    val dtNasc: String = "",
    val curso: String = ""
) : Parcelable