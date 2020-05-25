package com.example.zvent.results

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController

import com.example.zvent.R
import com.example.zvent.data.InvitadosUser
import com.example.zvent.databinding.ResultsFragmentBinding

class ResultsFragment : Fragment() {

    companion object {
        fun newInstance() = ResultsFragment()
    }

    private lateinit var viewModel: ResultsViewModel
    private lateinit var viewModelFactory: ResultsViewModelFactory

    private  lateinit var binding: ResultsFragmentBinding
    private lateinit var invitadosUser: InvitadosUser

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.results_fragment,
            container,
            false
        )

        binding.verInvitadosButton.setOnClickListener {
            Toast.makeText(this.context, viewModel.asistentesTxt.value!!, Toast.LENGTH_SHORT).show()

        }

        binding.reiniciarButton.setOnClickListener{
            requireView().findNavController().navigate(ResultsFragmentDirections.actionResultsFragmentToListFragment())
        }


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModelFactory = ResultsViewModelFactory(invitadosUser.invitados)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ResultsViewModel::class.java)


        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try{
            invitadosUser = context as InvitadosUser
        } catch (castException: ClassCastException){

        }
    }


}
