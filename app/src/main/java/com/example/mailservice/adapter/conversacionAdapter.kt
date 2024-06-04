package com.example.mailservice.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mailservice.R
import com.example.mailservice.objects.Mensaje

class conversacionAdapter(private val conversaciones: List<Mensaje>): RecyclerView.Adapter<conversacionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): conversacionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return conversacionViewHolder(layoutInflater.inflate(R.layout.conversacion, parent, false))
    }


    override fun onBindViewHolder(holder: conversacionViewHolder, position: Int) {
        val item = conversaciones[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = conversaciones.size

}