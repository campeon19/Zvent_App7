package com.example.zvent.new_rol

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zvent.database.RolDatabaseDao
import java.lang.IllegalArgumentException

class AddRolViewModelFactory (private  val database: RolDatabaseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AddRolViewModel::class.java)){
            return AddRolViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}