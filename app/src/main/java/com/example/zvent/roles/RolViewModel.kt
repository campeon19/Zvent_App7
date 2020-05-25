package com.example.zvent.roles

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.zvent.database.Rol
import com.example.zvent.database.RolDatabaseDao
import com.example.zvent.database.RolInvitado
import java.lang.StringBuilder

class RolViewModel(val database: RolDatabaseDao) : ViewModel() {

    private val roles = database.getroles()

    val rolText = Transformations.map(roles){
        buildInvitadoText(it)
    }

    private fun buildInvitadoText(roles: List<Rol>): String{
        val rolText = StringBuilder()
        for (rol in roles){
            rolText.append("Rol: ${rol.rolId}\nNombre: ${rol.rol}\nDescripcion: ${rol.descripcion}\nOrden: ${rol.orden}\n\n")
        }
        return rolText.toString()
    }

}
