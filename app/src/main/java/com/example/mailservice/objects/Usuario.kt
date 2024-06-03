package com.example.mailservice.objects

import java.io.Serializable

data class Usuario(val id:Int, val name:String, val email:String, val password:String, val whoAmI: String, val activo: Boolean): Serializable
