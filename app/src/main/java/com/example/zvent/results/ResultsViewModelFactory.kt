package com.example.zvent.results

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zvent.data.Invitados
import java.lang.IllegalArgumentException

class ResultsViewModelFactory(private  val results: MutableList<Invitados>) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
      if(modelClass.isAssignableFrom(ResultsViewModel::class.java)){
          return ResultsViewModel(results) as T
      }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}