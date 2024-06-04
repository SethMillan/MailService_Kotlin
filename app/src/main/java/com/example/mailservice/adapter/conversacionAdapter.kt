package com.example.mailservice.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mailservice.R
import com.example.mailservice.objects.Mensaje
import com.example.mailservice.responderConversacion

class conversacionAdapter(private val conversaciones: List<Mensaje>): RecyclerView.Adapter<conversacionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): conversacionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return conversacionViewHolder(layoutInflater.inflate(R.layout.conversacion, parent, false))
    }


    override fun onBindViewHolder(holder: conversacionViewHolder, position: Int) {
        val item = conversaciones[position]
        holder.render(item)
        holder.itemView.setOnClickListener{
            val intent = Intent(it.context, responderConversacion::class.java)
            intent.putExtra("idConversacion", conversaciones[position].idConversacion)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = conversaciones.size

}