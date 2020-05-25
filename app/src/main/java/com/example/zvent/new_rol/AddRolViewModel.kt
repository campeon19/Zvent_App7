package com.example.zvent.new_rol

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zvent.database.Rol
import com.example.zvent.database.RolDatabaseDao
import kotlinx.coroutines.*

class AddRolViewModel(val database: RolDatabaseDao) : ViewModel() {

    val rol = MutableLiveData<String>()

    val descripcion = MutableLiveData<String>()

    val orden = MutableLiveData<Int>()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun inserRol(){
        uiScope.launch {
            insert()
        }
    }

    private suspend fun insert(){
        withContext(Dispatchers.IO){
            database.insert(
                    Rol(rol = rol.value ?: "", descripcion = descripcion.value ?: "", orden = orden.value ?: 1))
        }
    }

}
