package com.example.zvent.results

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zvent.database.ZventDatabaseDao
import java.lang.IllegalArgumentException

class ResultsViewModelFactory(private  val database: ZventDatabaseDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
      if(modelClass.isAssignableFrom(ResultsViewModel::class.java)){
          return ResultsViewModel(database) as T
      }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}