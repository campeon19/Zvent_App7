package com.example.zvent.results

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zvent.data.Invitados

class ResultsViewModel(results: MutableList<Invitados>) : ViewModel() {

    var cont: Int = 0
    private val _resultsCount = MutableLiveData<Int>()
    val resultsCount: LiveData<Int>
        get() = _resultsCount

    private val _asistResult = MutableLiveData<Int>()
    val asistResult: LiveData<Int>
        get() = _asistResult

    private val _asistentesTxt = MutableLiveData<String>()
    val asistentesTxt : LiveData<String>
        get() = _asistentesTxt

    init {
        _resultsCount.value = results.size
        for(result in results){
            _asistentesTxt.value += "${result.nombre} : ${result.estado}\n"

            if (result.estado.equals("si")){
                cont++
            }
        }
        _asistResult.value = cont
    }

}
