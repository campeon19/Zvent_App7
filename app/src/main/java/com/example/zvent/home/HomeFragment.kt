package com.example.zvent.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController

import com.example.zvent.R
import com.example.zvent.databinding.HomeFragmentBinding

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.home_fragment,
            container,
            false
        )

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.finished()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        viewModel.stateInitialized.observe(viewLifecycleOwner, Observer { isStarted ->
            if(isStarted){
                requireView().findNavController().navigate(HomeFragmentDirections.actionHomeFragment2ToListFragment2())
            }
        })
    }

}
