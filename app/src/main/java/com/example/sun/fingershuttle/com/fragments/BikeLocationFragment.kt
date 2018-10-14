package com.example.sun.fingershuttle.com.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.sun.fingershuttle.R

class BikeLocationFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bike_location, container, false)
    }


    companion object {
        @JvmStatic
        fun newInstance() =
                BikeLocationFragment()
    }
}
