package com.example.sun.fingershuttle.com.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sun.fingershuttle.MinaUtil.SessionManager
import com.example.sun.fingershuttle.R
import kotlinx.android.synthetic.main.fragment_home.*

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        btn_ring.setOnClickListener {
            //ConnectUtil.sendMsg("#ring")
            SessionManager.writeMsg("1#ring")
        }

    }
}
