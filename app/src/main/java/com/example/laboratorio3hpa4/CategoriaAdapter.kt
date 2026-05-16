package com.example.laboratorio3hpa4

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

//Clase del adapter personalizado de las categorias para que contenga una imagen, un nombre y una descripcion
class CategoriaAdapter(
    private val context: Context,
    private val categorias: List<Categoria>
) : BaseAdapter() {

    override fun getCount(): Int {
        return categorias.size
    }
    override fun getItem(position: Int): Any {
        return categorias[position]
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    //Infla la vista del layout de cada item_categoria y setea los valores correspondientes
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val vista = convertView ?: LayoutInflater.from(context) // Reutiliza la vista si ya existe para evitar inflar innecesariamente
            .inflate(R.layout.item_categoria, parent, false)

        val imgCategoria = vista.findViewById<ImageView>(R.id.imgCategoria)
        val tvNombreCategoria = vista.findViewById<TextView>(R.id.tvNombreCategoria)
        val tvDescripcionCategoria = vista.findViewById<TextView>(R.id.tvDescripcionCategoria)

        val categoria = categorias[position]

        imgCategoria.setImageResource(categoria.imagen)
        tvNombreCategoria.text = categoria.nombre
        tvDescripcionCategoria.text = categoria.descripcion

        return vista
    }
}