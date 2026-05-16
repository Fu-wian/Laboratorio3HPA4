package com.example.laboratorio3hpa4

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

//Clase del adapter personalizado de los eventos para que contenga una imagen, nombre evento, fecha y lugar.
class EventoAdapter(
    private val context: Context,
    private val eventos: List<Evento>
) : BaseAdapter() {

    override fun getCount(): Int {
        return eventos.size
    }

    override fun getItem(position: Int): Any {
        return eventos[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val vista = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_evento, parent, false)

        val imgEvento = vista.findViewById<ImageView>(R.id.imgEvento)
        val tvNombreEvento = vista.findViewById<TextView>(R.id.tvNombreEvento)
        val tvFechaEvento = vista.findViewById<TextView>(R.id.tvFechaEvento)
        val tvLugarEvento = vista.findViewById<TextView>(R.id.tvLugarEvento)

        val evento = eventos[position]

        imgEvento.setImageResource(evento.imagen)
        tvNombreEvento.text = evento.nombre
        tvFechaEvento.text = evento.fecha
        tvLugarEvento.text = evento.lugar

        return vista
    }
}