package com.example.sun.fingershuttle.com.fragments


import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.sun.fingershuttle.R
import com.example.sun.fingershuttle.databinding.FragmentMonitorBinding
import com.example.sun.fingershuttle.viewmodel.CarStateModel

class Fragment_Monitor : Fragment() {
    private lateinit var model:CarStateModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model=activity?.run {
            ViewModelProviders.of(this).get(CarStateModel::class.java)
        }?:throw Exception("Invaild Activity")

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = FragmentMonitorBinding.inflate(layoutInflater)
        model.power.value=10
        binding.viewmodel=model
        binding.setLifecycleOwner(activity)
        return binding.root
    }


    companion object {
        @JvmStatic
        fun newInstance() =
                Fragment_Monitor()
    }
}
