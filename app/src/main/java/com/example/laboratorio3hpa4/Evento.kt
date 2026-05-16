package com.example.laboratorio3hpa4

import java.io.Serializable

data class Evento(
    val nombre: String,
    val fecha: String,
    val lugar: String,
    val descripcion: String,
    val categoria: String,
    val imagen: Int
) : Serializable