package com.example.ejercicioindividual_32m6.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.ejercicioindividual_32m6.Model.ComandaRepository
import com.example.ejercicioindividual_32m6.Model.Modelo.Comanda
import com.example.ejercicioindividual_32m6.Model.Modelo.ComandaDAO
import com.example.ejercicioindividual_32m6.Model.Modelo.ComandaDataBase
import kotlinx.coroutines.launch


class ComandaViewModel(application: Application) : AndroidViewModel(application) {

    // se conecta con el repository
    private val repository: ComandaRepository
    val allComanda: LiveData<List<Comanda>> // livedata encargada de exponer la info del modelo


    // se inicializa
    init {

        // se obtiene la instancia del DAO
        val ComandaDAO = ComandaDataBase.getDataBase(application).getComandaDAO()
        repository = ComandaRepository(ComandaDAO)
        allComanda = repository.listAllComanda
    }

    // se llaman a las funciones del modelo y se activan las corrutinas
    fun insertComanda(comanda: Comanda) = viewModelScope.launch {
        repository.insertComanda(comanda)

        fun updateComanda(comanda: Comanda) = viewModelScope.launch {
            repository.updateComanda(comanda)
        }


        fun deleteComanda() = viewModelScope.launch {
            repository.deleteAll()
        }

        fun deleteunaComanda(comanda: Comanda) = viewModelScope.launch {
            repository.deleteunaComanda(comanda)
        }

        // para seleccionar algun elemento
        val selectComanda: MutableLiveData<Comanda?> = MutableLiveData()

        // funcion para recibir una tarea seleccionada desde el RV
        fun selected(comanda: Comanda?) {
            selectComanda.value = comanda
        }

        // funcion para mostrar los elementos luego de una seleccion recibir el objeto seleccionado
        fun selectedItem(): LiveData<Comanda?> = selectComanda
    }

    fun selected(it: Comanda) {
    }

    fun deleteComanda() {
        TODO("Not yet implemented") }

    fun updateComanda(nuevoConsumo1: Comanda) {
    }

    fun deleteUnaComanda(it1: Comanda) {
    }

}