package com.example.zvent.roles

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController

import com.example.zvent.R
import com.example.zvent.database.Rol
import com.example.zvent.database.ZventDatabase
import com.example.zvent.databinding.AddFragmentBinding
import com.example.zvent.databinding.RolFragmentBinding
import com.example.zvent.informacion.InformacionFragmentDirections

class RolFragment : Fragment() {

    companion object {
        fun newInstance() = RolFragment()
    }

    private lateinit var viewModelFactory: RolViewModelFactory
    private lateinit var binding: RolFragmentBinding
    private lateinit var viewModel: RolViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.rol_fragment,
            container,
            false
        )

        binding.fab.setOnClickListener{
            requireView().findNavController().navigate(RolFragmentDirections.actionRolFragmentToAddRolFragment())
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.lifecycleOwner = this

        val application = requireNotNull(this.activity).application
        val dataSource = ZventDatabase.getInstance(application).RolDatabaseDao

        viewModelFactory = RolViewModelFactory(dataSource)
        viewModel = ViewModelProvider(this, viewModelFactory).get(RolViewModel::class.java)

        binding.viewModel = viewModel
    }

}
