package com.example.zvent.addInvitado

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zvent.database.Invitados
import com.example.zvent.database.Rol
import com.example.zvent.database.RolDatabaseDao
import com.example.zvent.database.ZventDatabaseDao
import kotlinx.coroutines.*

class AddViewModel(
    val database: ZventDatabaseDao,
    val databaseRol: RolDatabaseDao
) : ViewModel() {

    val nombre = MutableLiveData<String>()
    val telefono = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val estado = MutableLiveData<String>()
    val rolLista = databaseRol.getroles()

    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun insertInvitado(rol: Any){
        uiScope.launch {
            insert(rol as Rol)
        }
    }

    private suspend fun insert(rol: Rol){
        withContext(Dispatchers.IO){
            database.insert(Invitados(nombre = nombre.value ?: "", telefono = telefono.value ?: "", email = email.value ?: "", estado = estado.value ?: "" , rol_id = rol.rolId ?: 0L))
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
