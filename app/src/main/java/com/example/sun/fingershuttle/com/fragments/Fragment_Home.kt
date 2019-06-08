package com.example.sun.fingershuttle.com.fragments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sun.fingershuttle.MinaUtil.SessionManager
import com.example.sun.fingershuttle.databinding.FragmentHomeBinding
import com.example.sun.fingershuttle.viewmodel.CarCurrentInfoModel
import kotlinx.android.synthetic.main.fragment_home.*

class Fragment_Home : Fragment(){
    private lateinit var model: CarCurrentInfoModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = activity?.run {
            ViewModelProviders.of(this).get(CarCurrentInfoModel::class.java)
        } ?: throw Exception("Invaild Activity")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding = FragmentHomeBinding.inflate(layoutInflater)
        model.speed.value = 0
        model.mileage.value = 50
        model.power.value = 100
        binding.model = model
        binding.setLifecycleOwner(activity)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                Fragment_Home()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setBtnClickListener()

    }

    private fun setBtnClickListener() {
        btn_ring.setOnClickListener {
            SessionManager.writeMsg("1#ring")
        }

        btn_light.setOnClickListener {
            SessionManager.writeMsg("1#light")
        }

        btn_lock.setOnClickListener {
            SessionManager.writeMsg("1#lock")
        }

        btn_resist.setOnClickListener {
            SessionManager.writeMsg("1#resist")
        }
    }
}
