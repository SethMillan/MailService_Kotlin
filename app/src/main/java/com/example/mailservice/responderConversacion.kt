package com.example.mailservice

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mailservice.adapter.mensajeAdapter
import com.example.mailservice.objects.Mensaje

class responderConversacion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_responder_conversacion)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerMensajes)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val mensajes = listOf(
            Mensaje(1, 1, "alice@example.com", "bob@example.com", "Reunion importante", "Hola Bob, solo quería recordarte sobre nuestra importante reunión mañana a las 10am. Por favor, llega a tiempo.", false, false),
            Mensaje(2, 1, "bob@example.com", "alice@example.com", "Re: Reunion importante", "Gracias Alice, estaré allí. ¿Podrías enviarme la agenda?", true, true),
            Mensaje(3, 2, "charlie@example.com", "equipo@example.com", "Anuncio de nuevo proyecto", "Hola equipo, estoy emocionado de anunciar nuestro nuevo proyecto, 'XYZ'. Trabajaremos en este proyecto durante los próximos meses.", true, true),
            Mensaje(4, 3, "david@example.com", "sarah@example.com", "Pregunta rápida", "Hola Sarah, tengo una pregunta rápida sobre el informe. ¿Puedes llamarme cuando estés libre?", false, false),
            Mensaje(5, 3, "sarah@example.com", "david@example.com", "Re: Pregunta rápida", "Claro David, te llamaré en 5 minutos.", true, true)
        )
        recyclerView.adapter=mensajeAdapter(mensajes)
    }
}