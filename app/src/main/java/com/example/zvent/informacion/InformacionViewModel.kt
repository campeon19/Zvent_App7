package com.example.zvent.informacion

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.zvent.database.RolInvitado
import com.example.zvent.database.ZventDatabaseDao
import kotlinx.coroutines.*
import java.lang.StringBuilder

class InformacionViewModel(val database: ZventDatabaseDao) : ViewModel() {


    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val invitados = database.getInvitadoRol()

    val invitadosText = Transformations.map(invitados){
        buildInvitadosText(it)
    }

    private fun buildInvitadosText(invitados: List<RolInvitado>): String{
        val invitadosText = StringBuilder()
        for (invitado in invitados){
            invitadosText.append("${invitado.invitados.nombre}\nTelefono: ${invitado.invitados.telefono}\nEmail: ${invitado.invitados.email}\nAsistencia:${invitado.invitados.estado}\nRol: ${invitado.rol}\n\n")
        }
        return invitadosText.toString()
    }


/*    private val _informacion = MutableLiveData<String>()
    val informacion : LiveData<String>
        get() = _informacion

    init {
        for(result in results){
            _informacion.value += "${result.nombre}\nTelefono: ${result.telefono}\nEmail: ${result.email}\nAsistencia:${result.estado}\n\n"
        }
    }*/

}
