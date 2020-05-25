package com.example.zvent.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zvent.data.Invitados

class ListViewModel : ViewModel() {
    private val _invitado = MutableLiveData<Invitados>()
    val invitado: MutableLiveData<Invitados>
        get() = _invitado

    fun updateInvitado(invitado: Invitados){
        _invitado.value = invitado
    }
}
