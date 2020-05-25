package com.example.zvent.data

data class Invitados(
    val nombre: String,
    val telefono: String,
    val email: String,
    var estado: String = "no"
)