package com.example.mailservice.objects

import java.io.Serializable

data class Mensaje(
    val id: Int,
    val idConversacion: Int,
    val correoEmisor: String,
    val correoReceptor: String,
    val asunto: String,
    val mensaje: String,
    val recibido: Boolean,
    val leido: Boolean
): Serializable
