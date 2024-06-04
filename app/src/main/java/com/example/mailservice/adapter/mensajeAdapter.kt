package com.example.mailservice.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mailservice.R
import com.example.mailservice.objects.Mensaje
import com.example.mailservice.responderConversacion

class mensajeAdapter(private val mensajes: List<Mensaje>):RecyclerView.Adapter<mensajeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): mensajeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return mensajeViewHolder(layoutInflater.inflate(R.layout.mensaje, parent, false))
    }
    override fun onBindViewHolder(holder: mensajeViewHolder, position: Int) {
        val item = mensajes[position]
        holder.render(item)

    }
    override fun getItemCount(): Int = mensajes.size
}