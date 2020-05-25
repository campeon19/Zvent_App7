package com.example.zvent.informacion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zvent.data.Invitados

class InformacionViewModel(results: MutableList<Invitados>) : ViewModel() {

    private val _informacion = MutableLiveData<String>()
    val informacion : LiveData<String>
        get() = _informacion

    init {
        for(result in results){
            _informacion.value += "${result.nombre}\nTelefono: ${result.telefono}\nEmail: ${result.email}\nAsistencia:${result.estado}\n\n"
        }
    }

}
