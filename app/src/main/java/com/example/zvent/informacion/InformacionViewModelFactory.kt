package com.example.zvent.informacion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zvent.database.ZventDatabaseDao
import java.lang.IllegalArgumentException

class InformacionViewModelFactory (private  val database: ZventDatabaseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(InformacionViewModel::class.java)){
            return InformacionViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}