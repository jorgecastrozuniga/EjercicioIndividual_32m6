package com.example.ejercicioindividual_32m6.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ejercicioindividual_32m6.Model.Modelo.Comanda
import com.example.ejercicioindividual_32m6.Model.Modelo.ComandaDataBase
import com.example.ejercicioindividual_32m6.R
import com.example.ejercicioindividual_32m6.ViewModel.ComandaViewModel
import com.example.ejercicioindividual_32m6.databinding.FragmentFirstBinding


class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private val binding get() = _binding!!


    // instancia del viewModel

    private val viewModel: ComandaViewModel by activityViewModels()
    private var idComanda: Int = 0
    private var comandaseleccionada: Comanda? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        val database = Room.databaseBuilder(
            requireContext().applicationContext,
            ComandaDataBase::class.java,
            "Comanda"
        )
            .build()


        binding.btagregar.setOnClickListener {
            guardarDatos()
        }

        binding.btmostrar.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.btborrar.setOnClickListener {
            viewModel.deleteComanda()
            Toast.makeText(context, "Todas las comandas han sido borradas", Toast.LENGTH_SHORT)
                .show()
        }

        viewModel.comandaseleccionada().observe(viewLifecycleOwner) {
            it?.let { comandaElegida ->
                binding.textInputLayoutDescripcion.editText?.setText(comandaElegida.descripcion)
                binding.textInputLayoutPrecio.editText?.setText(comandaElegida.precio.toString())
                binding.textInputLayoutCantidad.editText?.setText(comandaElegida.cantidad.toString())
                val totalidad = comandaElegida.precio * comandaElegida.cantidad
                binding.textViewTotal.setText(totalidad.toString())
                idComanda = comandaElegida.id
                comandaseleccionada = comandaElegida
            }
        }


        binding.btdeleteone.setOnClickListener {
            comandaseleccionada?.let { it1 -> viewModel.deleteUnaComanda(it1) }
            Toast.makeText(context, "Consumo borrado de base de datos", Toast.LENGTH_LONG).show()
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun guardarDatos() {
        var total = binding.textInputLayoutCantidad.editText?.text.toString().toInt() *
                binding.textInputLayoutPrecio.editText?.text.toString().toInt()
        val descripcion = binding.textInputLayoutDescripcion.editText?.text.toString()

        if (descripcion.isEmpty()) {
            binding.textInputLayoutDescripcion.editText?.setError("Ingrese un producto")
        }
        val precio = binding.textInputLayoutPrecio.editText?.text.toString().toInt()
        if (precio == null) {
            binding.textInputLayoutPrecio.editText?.setError("Ingrese un precio")
        }
        val cantidad = binding.textInputLayoutCantidad.editText?.text.toString().toInt()
        if (cantidad == null) {
            binding.textInputLayoutCantidad.editText?.setError("Ingrese una cantidad")
        }

        total = total
        binding.textViewTotal.text = "Total\n $total"

        if (descripcion.isEmpty() || precio == null || cantidad == null) {
            Toast.makeText(context, "Ingrese los datos del consumo", Toast.LENGTH_LONG).show()

        } else

            if (idComanda == 0) {
                val nuevaComanda = Comanda(
                    descripcion = descripcion,
                    precio = precio,
                    cantidad = cantidad,
                    total = total
                )
                viewModel.insertComanda(nuevaComanda)
                Toast.makeText(context, "Consumo guardado exitosamente", Toast.LENGTH_LONG).show()

            } else {

                val nuevoConsumo1 = Comanda(
                    id = idComanda,
                    descripcion = descripcion,
                    precio = precio,
                    cantidad = cantidad,
                    total = total
                )
                viewModel.updateComanda(nuevoConsumo1)
               Toast.makeText(context, "Consumo actualizado exitosamente", Toast.LENGTH_LONG).show()
            }
    }
}

