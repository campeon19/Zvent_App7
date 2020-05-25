package com.example.zvent.results

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController

import com.example.zvent.R
import com.example.zvent.database.ZventDatabase
import com.example.zvent.databinding.ResultsFragmentBinding

class ResultsFragment : Fragment() {

    companion object {
        fun newInstance() = ResultsFragment()
    }

    private lateinit var viewModel: ResultsViewModel
    private lateinit var viewModelFactory: ResultsViewModelFactory
    private  lateinit var binding: ResultsFragmentBinding


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
            Toast.makeText(this.context, viewModel.resultsText.value!!, Toast.LENGTH_SHORT).show()

        }

    /*    binding.reiniciarButton.setOnClickListener{
            requireView().findNavController().navigate(ResultsFragmentDirections.actionResultsFragmentToListFragment())
        }*/


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val application = requireNotNull(this.activity).application
        val dataSource = ZventDatabase.getInstance(application).ZventDatabaseDao

        viewModelFactory = ResultsViewModelFactory(dataSource)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ResultsViewModel::class.java)


        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.reiniciarButton.setOnClickListener{
            viewModel.restart()
        }


    }

    /*override fun onAttach(context: Context) {
        super.onAttach(context)
        try{
            invitadosUser = context as InvitadosUser
        } catch (castException: ClassCastException){

        }
    }*/


}
