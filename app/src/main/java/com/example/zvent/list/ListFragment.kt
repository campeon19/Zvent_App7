package com.example.zvent.list

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI

import com.example.zvent.R
import com.example.zvent.data.Invitados
import com.example.zvent.data.InvitadosUser
import com.example.zvent.databinding.ListFragmentBinding
import java.lang.ClassCastException

class ListFragment : Fragment() {

    companion object {
        fun newInstance() = ListFragment()
    }

    private lateinit var viewModel: ListViewModel
    private lateinit var binding: ListFragmentBinding

    private lateinit var invitadosUser: InvitadosUser
    private  var invitadosIndex: Int= -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.list_fragment,
            container,
            false
        )
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        binding.viewModel = viewModel
        updateInfoInvitados()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.asist){
            invitadosUser.invitados[invitadosIndex].estado = "si"
            if (!updateInfoInvitados()){
                requireView().findNavController().navigate(ListFragmentDirections.actionListFragment2ToResultsFragment2())

            }

        }
        if(item.itemId == R.id.noAsist){
            invitadosUser.invitados[invitadosIndex].estado = "no"
            if (!updateInfoInvitados()){
                requireView().findNavController().navigate(ListFragmentDirections.actionListFragment2ToResultsFragment2())


            }
        }

        return super.onOptionsItemSelected(item)
    }

    fun updateInfoInvitados(): Boolean{
        invitadosIndex++
        if(invitadosUser.invitados.size > invitadosIndex){
            viewModel.updateInvitado(invitadosUser.invitados[invitadosIndex])
            (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.titulo_invitados, invitadosIndex + 1, invitadosUser.invitados.size )
            return true
        }
        return false

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            invitadosUser = context as InvitadosUser
        } catch (castException: ClassCastException){

        }
    }



}
