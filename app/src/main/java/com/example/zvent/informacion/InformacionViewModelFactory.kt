package com.example.zvent.informacion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zvent.data.Invitados
import java.lang.IllegalArgumentException

class InformacionViewModelFactory (private  val results: MutableList<Invitados>) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(InformacionViewModel::class.java)){
            return InformacionViewModel(results) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}