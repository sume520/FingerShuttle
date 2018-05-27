package com.example.sun.fingershuttle.com.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amap.api.maps2d.AMap
import com.amap.api.maps2d.MapView

import com.example.sun.fingershuttle.R
import kotlinx.android.synthetic.main.fragment_map.*
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.find

class Fragment_Map : Fragment() {
    lateinit var mapView:MapView
    lateinit var aMap:AMap

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view=inflater.inflate(R.layout.fragment_map, container, false)
        mapView=view!!.findViewById(R.id.amapview)
        mapView.onCreate(savedInstanceState)
        init()
        // Inflate the layout for this fragment
        return view
    }

    private fun init(){
        aMap=mapView.map
    }

    companion object {

        @JvmStatic
        fun newInstance() =
                Fragment_Map()
    }
}
