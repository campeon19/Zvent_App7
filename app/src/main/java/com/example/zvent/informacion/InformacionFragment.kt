package com.example.zvent.informacion

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController

import com.example.zvent.R
import com.example.zvent.database.ZventDatabase
import com.example.zvent.databinding.InformacionFragmentBinding

class InformacionFragment : Fragment() {

    companion object {
        fun newInstance() = InformacionFragment()
    }

    private lateinit var viewModelFactory: InformacionViewModelFactory
    private lateinit var viewModel: InformacionViewModel

    private lateinit var binding: InformacionFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.informacion_fragment,
            container,
            false
        )

        binding.fab.setOnClickListener{
            requireView().findNavController().navigate(InformacionFragmentDirections.actionInformacionFragmentToAddFragment())
        }

        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val application = requireNotNull(this.activity).application
        val dataSource = ZventDatabase.getInstance(application).ZventDatabaseDao

        viewModelFactory = InformacionViewModelFactory(dataSource)
        viewModel = ViewModelProvider(this, viewModelFactory).get(InformacionViewModel::class.java)


        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

}
