package com.example.zvent.results

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.zvent.database.Invitados
import com.example.zvent.database.RolInvitado
import com.example.zvent.database.ZventDatabaseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.Job
import java.lang.StringBuilder

class ResultsViewModel(val database: ZventDatabaseDao) : ViewModel() {

    var invitados = database.getInvitados()
    var totalInvitados = database.getInvitadosTotal()
    var totalAsistentes = database.getInvitadoRegistrado()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _restartRegister = MutableLiveData<Boolean>()
    val restartRegister: LiveData<Boolean>
        get() = _restartRegister

    private val _eventSeeGuests = MutableLiveData<Boolean>()
    val eventSeeGuests: LiveData<Boolean>
        get() = _eventSeeGuests


    val resultsText = Transformations.map(invitados){
        buildResultsText(it)
    }

    fun restart(){
        _restartRegister.value = totalInvitados.value ?: 0>0
    }

    fun restartComplete() {
        _restartRegister.value = false
    }

    fun verInvitados(){
        _eventSeeGuests.value = true
    }

    private fun buildResultsText(invitados: List<Invitados>): String {
        val resultsText = StringBuilder()
        for(invitado in invitados){
            resultsText.append("${invitado.nombre}: ${invitado.estado}")
        }
        return resultsText.toString()
    }





    /*var cont: Int = 0
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
    }*/

}
