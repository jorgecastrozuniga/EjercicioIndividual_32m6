package com.example.ejercicioindividual_32m6.View

import android.icu.text.Transliterator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicioindividual_32m6.Model.Modelo.Comanda
import com.example.ejercicioindividual_32m6.databinding.ItemListBinding




class ComandaAdapter: RecyclerView.Adapter<ComandaAdapter.ComandaVH>(){


    // Lista de datos
    private var mlistComanda = listOf<Comanda>()

    /*************** para seleccionar*********************/
    // un elemento seleccionado para ocupar en la funcion
    private val selectedComanda = MutableLiveData<Comanda>()

    // funcion para seleccionar
    fun selectedItem(): LiveData<Comanda> = selectedComanda



    fun update(listComanda: List<Comanda>){
        mlistComanda= listComanda
        notifyDataSetChanged()
    }


    inner class ComandaVH(private val binding: ItemListBinding): // ItemListBinding es el inflado del xml
    RecyclerView.ViewHolder(binding.root),

    View.OnClickListener {

        fun bind(comanda: Comanda){

            binding.tvDescripcion.text= comanda.descripcion
            binding.tvPrecio.text = comanda.precio.toString()
            binding.tvCantidad.text = comanda.cantidad.toString()
            binding.tvTotal.text = comanda.total.toString()

            // activar elemento dentro de la vista
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            selectedComanda.value = mlistComanda[adapterPosition]
        }
    }

       override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComandaVH {
           return ComandaVH(ItemListBinding.inflate(LayoutInflater.from(parent.context)))
       }

       override fun getItemCount(): Int = mlistComanda.size

       override fun onBindViewHolder(holder: ComandaVH, position: Int) {
           val Comanda = mlistComanda[position]
           holder.bind(Comanda)
       }
   }