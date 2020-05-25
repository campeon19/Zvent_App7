package com.example.zvent.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zvent.database.ZventDatabaseDao
import java.lang.IllegalArgumentException

class ListViewModelFactory (private  val database: ZventDatabaseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ListViewModel::class.java)){
            return ListViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}