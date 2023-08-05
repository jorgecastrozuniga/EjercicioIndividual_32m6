package com.example.ejercicioindividual_32m6.Model.Modelo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update



@Dao
interface ComandaDAO{

    // insertar funciones a utilizar
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarComanda(comanda: Comanda)


@Update
suspend fun updateComanda(comanda: Comanda)

@Query("DELETE FROM TABLA_COMANDA")
suspend fun deleteAll()

@Query("SELECT * FROM TABLA_COMANDA")
fun getAllComanda(): LiveData<List<Comanda>>

// para borrar solo una lista de comanda seleccionada
@Delete
suspend fun deleteunaComanda(comanda: Comanda)

}