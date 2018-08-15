package com.example.sun.fingershuttle.com.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sun.fingershuttle.HelppingActivity
import com.example.sun.fingershuttle.R
import com.example.sun.fingershuttle.R.id.security_center
import kotlinx.android.synthetic.main.fragment_help.*

class Fragment_Help : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_help, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                Fragment_Help()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        security_center.setOnClickListener {
            val intent= Intent(activity,HelppingActivity::class.java)
            intent.putExtra("name","security_center")
            startActivity(intent)
        }
        custom_service.setOnClickListener {
            val intent= Intent(activity,HelppingActivity::class.java)
            intent.putExtra("name","custom_service")
            startActivity(intent)
        }
        urgency.setOnClickListener {
            val intent= Intent(activity,HelppingActivity::class.java)
            intent.putExtra("name","urgency")
            startActivity(intent)
        }

    }
}
