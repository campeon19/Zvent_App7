package com.example.zvent.new_rol

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController

import com.example.zvent.R
import com.example.zvent.addInvitado.AddFragmentDirections
import com.example.zvent.database.ZventDatabase
import com.example.zvent.databinding.AddRolFragmentBinding

class AddRolFragment : Fragment() {

    companion object {
        fun newInstance() = AddRolFragment()
    }

    private lateinit var binding: AddRolFragmentBinding
    private lateinit var viewModelFactory: AddRolViewModelFactory
    private lateinit var viewModel: AddRolViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.add_rol_fragment,
            container,
            false
        )

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.lifecycleOwner = this
        val application = requireNotNull(this.activity).application
        val dataSource = ZventDatabase.getInstance(application).RolDatabaseDao

        viewModelFactory = AddRolViewModelFactory(dataSource)
        viewModel = ViewModelProvider(this, viewModelFactory).get(AddRolViewModel::class.java)

        viewModel.orden.observe(viewLifecycleOwner, Observer { order ->
            binding.valorSeekBar.text = order.toString()
        })

        binding.viewModel = viewModel

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.save_manu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.save_button){
            viewModel.inserRol()
            requireView().findNavController().navigate(AddRolFragmentDirections.actionAddRolFragmentToRolFragment())
        }

        return super.onOptionsItemSelected(item)
    }

}
