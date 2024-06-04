package com.example.mailservice.adapter

import android.content.Intent
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.mailservice.MailActivity
import com.example.mailservice.R
import com.example.mailservice.objects.Mensaje

class conversacionViewHolder(view:View): RecyclerView.ViewHolder(view) {

    val email = view.findViewById<TextView>(R.id.txtEmailConversacion)
    val asunto = view.findViewById<TextView>(R.id.txtAsuntoConversacion)
    fun render(mensaje: Mensaje){
        email.text = mensaje.correoEmisor
        asunto.text = mensaje.asunto
    }
}