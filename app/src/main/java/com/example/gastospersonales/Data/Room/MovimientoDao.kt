
package com.example.gastospersonales.Data.Room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.gastospersonales.Data.Model.RegistroDeMovimientos
import kotlinx.coroutines.flow.Flow

@Dao
interface MovimientoDao {

    @Insert
    suspend fun insertarYObtenerId(
        movimiento: RegistroDeMovimientos
    ): Long

    @Update
    suspend fun actualizar(movimiento: RegistroDeMovimientos)

    @Delete
    suspend fun eliminar(movimiento: RegistroDeMovimientos)

    @Query("DELETE FROM movimientos WHERE Id = :id")
    suspend fun eliminarPorId(id: Int)

    @Query("SELECT * FROM movimientos ORDER BY FechaHora DESC")
    fun obtenerTodos(): Flow<List<RegistroDeMovimientos>>

    @Query("SELECT * FROM movimientos WHERE Id = :id")
    suspend fun obtenerPorId(id: Int): RegistroDeMovimientos?
}
