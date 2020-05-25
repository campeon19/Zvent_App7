package com.example.zvent.database

import androidx.lifecycle.LiveData

class RolRepository(private val getDatabaseDao: ZventDatabaseDao) {

    val allInvitados: LiveData<List<Invitados>> = getDatabaseDao.getInvitados()

    suspend fun addInvitado(invitados: Invitados){
        getDatabaseDao.addInvitado(invitados)
    }
}