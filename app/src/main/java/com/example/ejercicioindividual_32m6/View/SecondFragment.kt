package com.example.ejercicioindividual_32m6.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicioindividual_32m6.Model.Modelo.Comanda
import com.example.ejercicioindividual_32m6.R
import com.example.ejercicioindividual_32m6.ViewModel.ComandaViewModel
import com.example.ejercicioindividual_32m6.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    private val binding get() = _binding!!

    private val viewModel: ComandaViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }


        val adapter = ComandaAdapter()
        val recyclerView = binding.rv1
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )


        viewModel.allComanda.observe(viewLifecycleOwner) {
            it?.let {
                adapter.update(it)
            }
        }


        adapter.selectedItem().observe(viewLifecycleOwner) {
            it?.let {
                viewModel.selected(it)
                Log.d("que recibo", it.descripcion)
                findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}