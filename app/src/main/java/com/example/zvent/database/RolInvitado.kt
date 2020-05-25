package com.example.zvent.database

import androidx.room.Embedded

data class RolInvitado (

    @Embedded
    val invitados: Invitados,

    val rol: String
)