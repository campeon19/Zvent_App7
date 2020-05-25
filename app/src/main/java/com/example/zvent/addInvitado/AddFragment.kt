package com.example.zvent.addInvitado

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.zvent.MainActivity

import com.example.zvent.R
import com.example.zvent.databinding.AddFragmentBinding

class AddFragment : Fragment() {

    companion object {
        fun newInstance() = AddFragment()
    }

    private lateinit var binding: AddFragmentBinding

    private lateinit var viewModel: AddViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.add_fragment,
            container,
            false
        )
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddViewModel::class.java)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.save_manu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.save_button){
            if (TextUtils.isEmpty(binding.nomEdit.text.toString())){
                binding.nomEdit.error = "Ingresar el nombre"
            } else if(TextUtils.isEmpty(binding.emEdit.text.toString())){
                binding.emEdit.error = "Ingresar el correo electronico"
            } else if(TextUtils.isEmpty(binding.telEdit.text.toString())){
                binding.telEdit.error = "Ingresar el numero de telefono"
            } else{
                (activity as MainActivity).addInvitado(binding.nomEdit.text.toString(), binding.telEdit.text.toString(), binding.emEdit.text.toString())
                //activity?.onBackPressed()
                requireView().findNavController().navigate(AddFragmentDirections.actionAddFragmentToInformacionFragment())
            }

        }

        return super.onOptionsItemSelected(item)
    }

}
