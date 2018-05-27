package com.example.sun.fingershuttle.com.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sun.fingershuttle.R
import com.example.sun.fingershuttle.R.id.btn_buzz
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.support.v4.toast

class Fragment_Home : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(
                R.layout.fragment_home,
                container,
                false
        )
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                Fragment_Home()
    }

}
