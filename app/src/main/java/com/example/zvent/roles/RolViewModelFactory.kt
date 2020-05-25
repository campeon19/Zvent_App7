package com.example.zvent.roles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zvent.database.RolDatabaseDao
import java.lang.IllegalArgumentException

class RolViewModelFactory (private  val database: RolDatabaseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RolViewModel::class.java)){
            return RolViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}