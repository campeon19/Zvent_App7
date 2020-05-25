package com.example.zvent.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.SET_DEFAULT
import androidx.room.PrimaryKey

@Entity(tableName = "invitado_table", foreignKeys = [ForeignKey(entity = Rol::class, parentColumns = ["id"], childColumns = ["rol_id"], onDelete = SET_DEFAULT)])
data class Invitados (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var invitadoId: Long = 0L,

    @NonNull
    var nombre: String,
    var telefono: String,
    var email: String,
    var estado: String = "no",

    @ColumnInfo(name = "rol_id", index = true)
    var rol_id: Long = 0L
)