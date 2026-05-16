package com.example.laboratorio3hpa4

import java.io.Serializable

data class Categoria(
    val nombre: String,
    val descripcion: String,
    val imagen: Int
) : Serializable