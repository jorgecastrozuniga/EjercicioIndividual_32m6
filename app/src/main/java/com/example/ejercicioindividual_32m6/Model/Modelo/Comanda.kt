package com.example.ejercicioindividual_32m6.Model.Modelo

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull


@Entity(tableName = "tabla_comanda")

data class Comanda (

   // se define la llave primaria
   @PrimaryKey(autoGenerate = true)
   @NotNull

   // se definen los campos de la clase
   val id: Int=0,
   val descripcion: String,
   val precio: Int,
   val cantidad: Int,
   val total: Int,

        )