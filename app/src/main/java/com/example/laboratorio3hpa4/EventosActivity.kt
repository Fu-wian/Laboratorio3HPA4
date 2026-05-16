package com.example.laboratorio3hpa4

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class EventosActivity : AppCompatActivity() {

    //En caso de que se quiera entrar un intent explicito. Poner una variable que mande un link?

    private val eventos = listOf(
        Evento(
            "Charla de Inteligencia Artificial",
            "15 de junio",
            "Auditorio principal",
            "Conferencia sobre aplicaciones actuales de la inteligencia artificial.",
            "Académico",
            R.drawable.academico
        ),
        Evento(
            "Taller de Kotlin",
            "18 de junio",
            "Laboratorio 3",
            "Taller práctico sobre desarrollo Android con Kotlin.",
            "Académico",
            R.drawable.academico
        ),
        Evento(
            "Festival Cultural Universitario",
            "20 de junio",
            "Plaza central",
            "Presentaciones artísticas y culturales de estudiantes.",
            "Cultural",
            R.drawable.cultural
        ),
        Evento(
            "Exposición de Arte",
            "22 de junio",
            "Salón de eventos",
            "Muestra artística organizada por estudiantes.",
            "Cultural",
            R.drawable.cultural
        ),
        Evento(
            "Torneo de Fútbol",
            "25 de junio",
            "Cancha universitaria",
            "Competencia deportiva entre facultades.",
            "Deportivo",
            R.drawable.deportivo
        ),
        Evento(
            "Carrera 5K",
            "28 de junio",
            "Campus universitario",
            "Actividad deportiva para estudiantes y docentes.",
            "Deportivo",
            R.drawable.deportivo
        )
    )
    //Lista de informacion de los eventos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.evento)
        val tvCategoria = findViewById<TextView>(R.id.tvCategoria)
        val listViewEventos = findViewById<ListView>(R.id.listViewEventos)

        val categoriaRecibida = intent.getStringExtra(MainActivity.EXTRA_CATEGORIA)

        tvCategoria.text = categoriaRecibida

        //Filtra los eventos por categoria utilizando la funcion filter que devuelve una lista con los elementos que cumplan con la condicion dada.
        val eventosFiltrados = eventos.filter {
            it.categoria == categoriaRecibida
        }

        val adapter = EventoAdapter(this, eventosFiltrados)
        listViewEventos.adapter = adapter

        //Falta ponerle accion al listview si es que se mantiene,
        //Si cambiar a otro contenedor modificar todo lo que esta aqui.

    }
}