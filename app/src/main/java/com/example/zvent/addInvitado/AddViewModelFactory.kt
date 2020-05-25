package com.example.zvent.addInvitado

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zvent.database.RolDatabaseDao
import com.example.zvent.database.ZventDatabaseDao
import java.lang.IllegalArgumentException

class AddViewModelFactory (private  val database: ZventDatabaseDao, private val databaseRol: RolDatabaseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AddViewModel::class.java)){
            return AddViewModel(database, databaseRol) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}