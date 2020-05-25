package com.example.zvent.list


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zvent.database.Invitados
import com.example.zvent.database.RolInvitado
import com.example.zvent.database.ZventDatabaseDao
import kotlinx.coroutines.*

class ListViewModel(val database: ZventDatabaseDao) : ViewModel() {

    val invitadosList = database.getInvitadoRol()

    private val _registeredComplete = MutableLiveData<Boolean>()
    val registeredComplete: LiveData<Boolean>
        get() = _registeredComplete

    var invitadoCount = 1
        private set
    var invitadosTotal = 0
        private set

    val currentinvitado = MutableLiveData<RolInvitado>()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun init (rol: List<RolInvitado>){
        invitadosTotal = rol.size
        if (rol.isEmpty()){
            _registeredComplete.value = true
        } else {
            currentinvitado.value = rol[invitadoCount -1]
        }
    }

    fun updateCurrentInvitado(){
        val rol = currentinvitado.value
        invitadoCount++
        if(invitadosTotal >= invitadoCount){
            currentinvitado.value = invitadosList.value?.get(invitadoCount -1)
        }
        uiScope.launch {
            update(rol?.let {
                Invitados(invitadoId = it.invitados.invitadoId,
                    nombre = it.invitados.nombre,
                    telefono = it.invitados.telefono,
                    email = it.invitados.email,
                    estado = "si",
                    rol_id = it.invitados.rol_id
                )
            })
        }
        if(invitadoCount> invitadosTotal){
            _registeredComplete.value = true
        }
    }



    fun updateCurrentInvitadoNo(){
        val rol = currentinvitado.value
        invitadoCount++
        if(invitadosTotal >= invitadoCount){
            currentinvitado.value = invitadosList.value?.get(invitadoCount -1)
        }
        uiScope.launch {
            update(rol?.let {
                Invitados(invitadoId = it.invitados.invitadoId,
                    nombre = it.invitados.nombre,
                    telefono = it.invitados.telefono,
                    email = it.invitados.email,
                    estado = "no",
                    rol_id = it.invitados.rol_id
                )
            })
        }
        if(invitadoCount> invitadosTotal){
            _registeredComplete.value = true
        }
    }

    suspend fun update(invitado: Invitados?){
        withContext(Dispatchers.IO){
            invitado?.let {
                database.update(it)
            }
        }
    }



    fun finishRegister(){
        _registeredComplete.value = false
        invitadoCount = 1
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


    /*private val _invitado = MutableLiveData<Invitados>()
    val invitado: MutableLiveData<Invitados>
        get() = _invitado

    fun updateInvitado(invitado: Invitados){
        _invitado.value = invitado
    }*/
}
