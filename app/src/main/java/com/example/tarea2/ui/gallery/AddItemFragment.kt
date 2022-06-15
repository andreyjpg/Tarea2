package com.example.tarea2.ui.gallery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.tarea2.R
import com.example.tarea2.databinding.FragmentAddItemBinding
import com.example.tarea2.model.Bodega
import com.example.tarea2.viewModel.BodegaViewModel

class AddItemFragment : Fragment() {
    private var _binding:FragmentAddItemBinding? = null
    private val binding get() = _binding!!
    private lateinit var bodegaViewModel: BodegaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        saveInstanceState: Bundle?
    ) : View {
        _binding = FragmentAddItemBinding.inflate(inflater, container, false)

        bodegaViewModel = ViewModelProvider(this).get(BodegaViewModel::class.java)

        binding.btAddItemForm.setOnClickListener { addLugar() }
        return binding.root
    }

    private fun addLugar() {
        val nombre = binding.inputName.text.toString()
        val value = binding.inputValue.text.toString().toInt()

        if(validation(nombre)) {
            val item = Bodega(0, nombre, value)
            bodegaViewModel.addItem(item)
            Toast.makeText(requireContext(), getString(R.string.msg_ItemAdded), Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(requireContext(), getString(R.string.msg_ItemError), Toast.LENGTH_LONG).show()
        }
    }

    private fun validation(nombre: String): Boolean {
        return !(nombre.isEmpty())
    }
}