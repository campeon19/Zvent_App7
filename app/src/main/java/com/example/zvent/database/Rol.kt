package com.example.zvent.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rol_table")
data class Rol (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var rolId: Long = 0L,

    @NonNull
    val rol: String,

    @ColumnInfo(name = "descripcion")
    val descripcion: String,

    @ColumnInfo(name = "orden")
    val orden: Int


){
    override fun toString(): String {
        return rol + descripcion
    }
}