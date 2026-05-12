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
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import java.io.Serializable


class MainActivity : AppCompatActivity() {
    //Clase de datos para las categorias
    data class Categoria(
        val nombre: String,
        val descripcion: String,
        val imagen: Int
    ) : Serializable

    companion object {
        const val EXTRA_CATEGORIA = "extra_categoria"
        //Permite la comunicacion entre activity

        //Lista de categorias con su nombre, descripcion y imagen
        val categorias = listOf(
            Categoria(
                "Académico",
                "Charlas, talleres y conferencias universitarias.",
                R.drawable.academico
            ),
            Categoria(
                "Cultural",
                "Actividades artísticas y culturales.",
                R.drawable.cultural
            ),
            Categoria(
                "Deportivo",
                "Torneos y actividades deportivas.",
                R.drawable.deportivo
            )
        ) //Esta informacion es lo que poblara el listview
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //Variable del listview de las categorias existentes
        val listViewCategorias = findViewById<ListView>(R.id.listViewCategorias)

        //Adapter del listview de las categorias
        val adapter = CategoriaAdapter(this, categorias)
        listViewCategorias.adapter = adapter

        //Click en el listview para ir a la otra categoria seleccionada
        //Utilizando el intent y el EXTRA_CATEGORIA para mandar la categoria seleccionada a la otra activity
        listViewCategorias.setOnItemClickListener { _, _, position, _ ->

            val categoriaSeleccionada = categorias[position]

            val intent = Intent(this, EventosActivity::class.java)
            intent.putExtra(EXTRA_CATEGORIA, categoriaSeleccionada.nombre)
            startActivity(intent)
        }

    }

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
}
