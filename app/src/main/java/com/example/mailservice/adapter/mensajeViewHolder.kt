package com.example.mailservice.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mailservice.R
import com.example.mailservice.objects.Mensaje

class mensajeViewHolder(view:View):RecyclerView.ViewHolder(view) {
    val remitente = view.findViewById<TextView>(R.id.remitente)
    val destinatario = view.findViewById<TextView>(R.id.destinatario)
    val asunto = view.findViewById<TextView>(R.id.asunto)
    val mensaje = view.findViewById<TextView>(R.id.mensaje)

    fun render(mensajeModel: Mensaje){
        remitente.text = mensajeModel.correoEmisor
        destinatario.text = mensajeModel.correoReceptor
        asunto.text = mensajeModel.asunto
        mensaje.text = mensajeModel.mensaje
    }

}