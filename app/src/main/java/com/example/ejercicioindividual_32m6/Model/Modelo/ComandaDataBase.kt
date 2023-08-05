package com.example.ejercicioindividual_32m6.Model.Modelo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Comanda::class], version = 1)
abstract class ComandaDataBase : RoomDatabase() {

    // se realiza conexion de la base de datos con el DAO a traves de funcion abstracta
    abstract fun getComandaDAO(): ComandaDAO

    // companion object expone un objeto sin instanciar la clase, es abstracto
    companion object {
        @Volatile
        private var INSTANCE: ComandaDataBase? = null

        // main thread // back thread hilos secundarios, volatile hace que se ejecuten donde esten disponibles las tareas asincronicas


        // el contexto es donde estamos ejecutando los procesos, hay !maneras de ejecutar
        fun getDataBase(context: Context): ComandaDataBase {
            val tempInntance = INSTANCE
            // cuando es !null
            if (tempInntance != null) {
                return tempInntance
            }

            //se llama al metodo que sincroniza para instanciar
            synchronized(this) {
                // la clase Room es la creadora de la instancia de la base de datos
                val instance = Room.databaseBuilder(
                    context.applicationContext, // permite que la BD sea una para toda la App
                    ComandaDataBase::class.java,  // nombre de archivo que contiene la BD
                    "Comanda"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}
