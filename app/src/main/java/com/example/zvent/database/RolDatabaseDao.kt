package com.example.zvent.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface RolDatabaseDao {

    @Insert
    fun insert(rol: Rol)

    @Update
    fun update(rol: Rol)

    @Query("SELECT * FROM rol_table WHERE id = :key")
    fun getRol(key:Long): Rol?

    @Query("SELECT * FROM rol_table")
    fun getroles(): LiveData<List<Rol>>

    @Query("SELECT COUNT(*) FROM rol_table")
    fun getRolCount(): LiveData<Int>

}