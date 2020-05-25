package com.example.zvent.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ZventDatabaseDao {

    @Insert
    fun insert(invitados: Invitados)

    @Update
    fun update(invitados: Invitados)

    @Query("SELECT * FROM INVITADO_TABLE WHERE id = :key")
    fun getInvitado(key: Long): Invitados?

    @Query("SELECT * FROM invitado_table ORDER BY id DESC")
    fun getInvitados(): LiveData<List<Invitados>>

    @Query("SELECT COUNT(*) FROM invitado_table")
    fun getInvitadosTotal(): LiveData<Int>

    @Query("SELECT COUNT(estado) FROM invitado_table WHERE estado = 1 ")
    fun getInvitadoRegistrado(): LiveData<Int>

    @Query("SELECT g.*, r.rol FROM invitado_table g LEFT JOIN rol_table r ON g.rol_id = r.id")
    fun getInvitadoRol(): LiveData<List<RolInvitado>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addInvitado(invitados: Invitados)
}