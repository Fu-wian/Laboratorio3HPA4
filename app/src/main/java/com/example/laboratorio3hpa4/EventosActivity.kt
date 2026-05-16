package com.example.laboratorio3hpa4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class EventosActivity : AppCompatActivity() {

    //En caso de que se quiera entrar un intent explicito. Poner una variable que mande un link?

    private val eventos = listOf(
        Evento(
            "Charla de Inteligencia Artificial",
            "15 de junio",
            "Ciudad del Saber",
            "Conferencia sobre aplicaciones actuales de la inteligencia artificial.",
            "Académico",
            R.drawable.inteligencia
        ),
        Evento(
            "Taller de Kotlin",
            "18 de junio",
            "Universidad Tecnológica de Panamá - Edificio 3.",
            "Taller práctico sobre desarrollo Android con Kotlin.",
            "Académico",
            R.drawable.kotlin
        ),
        Evento(
            "Festival Cultural Universitario",
            "20 de junio",
            "Universidad Tecnológica de Panamá",
            "Presentaciones artísticas y culturales de estudiantes.",
            "Cultural",
            R.drawable.festividad
        ),
        Evento(
            "Exposición de Arte",
            "22 de junio",
            "AltaPlaza Mall",
            "Muestra artística organizada por estudiantes.",
            "Cultural",
            R.drawable.arte
        ),
        Evento(
            "Torneo de Fútbol",
            "25 de junio",
            "Cancha de fútbol UTP",
            "Competencia deportiva entre facultades.",
            "Deportivo",
            R.drawable.futbol
        ),
        Evento(
            "Carrera 5K",
            "28 de junio",
            "Cinta Costera 3",
            "Actividad deportiva para estudiantes y docentes.",
            "Deportivo",
            R.drawable.carrera
        )
    )
    //Lista de informacion de los eventos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.evento)
        val tvCategoria = findViewById<TextView>(R.id.tvCategoria)
        val listViewEventos = findViewById<ListView>(R.id.listViewEventos)
        val btnVolver = findViewById<Button>(R.id.btnVolver)

        val categoriaRecibida = intent.getStringExtra(MainActivity.EXTRA_CATEGORIA)

        tvCategoria.text = categoriaRecibida

        //Filtra los eventos por categoria utilizando la funcion filter que devuelve una lista con los elementos que cumplan con la condicion dada.
        val eventosFiltrados = eventos.filter {
            it.categoria == categoriaRecibida
        }

        val adapter = EventoAdapter(this, eventosFiltrados)
        listViewEventos.adapter = adapter

        btnVolver.setOnClickListener {
            finish()
        }

        // RF-03: Al tocar un evento de la lista, se lanza un Intent explícito
        // hacia DetalleActivity pasando el objeto Evento completo usando Serializable
        listViewEventos.setOnItemClickListener { _, _, position, _ ->
            val eventoSeleccionado = eventosFiltrados[position]
            val intent = Intent(this, DetalleActivity::class.java)
            intent.putExtra(DetalleActivity.EXTRA_EVENTO, eventoSeleccionado)
            // putExtra envía el objeto Evento completo, posible gracias a que
            // la clase Evento implementa la interfaz Serializable
            startActivity(intent)

        }
    }
}