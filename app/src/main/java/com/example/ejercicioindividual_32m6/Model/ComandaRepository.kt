package com.example.ejercicioindividual_32m6.Model

import androidx.lifecycle.LiveData
import com.example.ejercicioindividual_32m6.Model.Modelo.Comanda
import com.example.ejercicioindividual_32m6.Model.Modelo.ComandaDAO




// Responsabilidad de exponer los datos para el ViewModel
class ComandaRepository(private val comandaDAO: ComandaDAO){

// Este value va a contener toda la informacion de la BD para todas las tareas
    val listAllComanda: LiveData<List<Comanda>> = comandaDAO.getAllComanda()


    suspend fun insertComanda(comanda: Comanda){
        comandaDAO.insertarComanda(comanda)
    }

    suspend fun updateComanda(comanda: Comanda){
        comandaDAO.updateComanda(comanda)
    }

    suspend fun deleteAll(){
        comandaDAO.deleteAll()
    }

    suspend fun deleteunaComanda(comanda: Comanda){
        comandaDAO.deleteunaComanda(comanda)
    }
}