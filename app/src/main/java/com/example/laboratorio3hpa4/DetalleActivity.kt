package com.example.laboratorio3hpa4
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.net.Uri
class DetalleActivity : AppCompatActivity(){
    companion object{
        const val EXTRA_EVENTO = "extra_evento"// Clave para recuperar el objeto Evento desde el Intent Se define en companion object para que sea accesible desde EventosActivity
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        // Referencias a los elementos del layout
        val imgDetalleEvento = findViewById<ImageView>(R.id.imgDetalleEvento)
        val tvDetalleNombre = findViewById<TextView>(R.id.tvDetalleNombre)
        val tvDetalleCategoria = findViewById<TextView>(R.id.tvDetalleCategoria)
        val tvDetalleFecha = findViewById<TextView>(R.id.tvDetalleFecha)
        val tvDetalleLugar = findViewById<TextView>(R.id.tvDetalleLugar)
        val tvDetalleDescripcion = findViewById<TextView>(R.id.tvDetalleDescripcion)
        val btnVerMapa = findViewById<Button>(R.id.btnVerMapa)
        val btnVolver = findViewById<Button>(R.id.btnVolver)

        // RF-03: Se recupera el objeto Evento completo enviado desde EventosActivity
        // usando getSerializableExtra con la clave definida en el companion object
        val evento = intent.getSerializableExtra(EXTRA_EVENTO) as Evento

        // Se muestran todos los campos del evento en la pantalla de detalle
        imgDetalleEvento.setImageResource(evento.imagen)
        tvDetalleNombre.text = evento.nombre
        tvDetalleCategoria.text = evento.categoria
        tvDetalleFecha.text = evento.fecha
        tvDetalleLugar.text = evento.lugar
        tvDetalleDescripcion.text = evento.descripcion

        // RF-04: Intent implícito para abrir el lugar del evento en una app de mapas
        // Se usa el esquema geo: con el nombre del lugar como query
        btnVerMapa.setOnClickListener {
            val uri = Uri.parse("geo:0,0?q=${evento.lugar}")
            val intentMapa = Intent(Intent.ACTION_VIEW, uri)
            if (intentMapa.resolveActivity(packageManager) != null) {
                startActivity(intentMapa)
            }else {
                // Fallback: si no hay app de mapas, se abre Google Maps en el navegador
                val uriBrowser = Uri.parse("https://maps.google.com/?q=${Uri.encode(evento.lugar)}")
                val intentBrowser = Intent(Intent.ACTION_VIEW, uriBrowser)
                startActivity(intentBrowser)
            }
        }
        btnVolver.setOnClickListener {
            finish()
        }
}
}