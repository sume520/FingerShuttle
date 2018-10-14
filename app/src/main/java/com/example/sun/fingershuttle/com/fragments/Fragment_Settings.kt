package com.example.sun.fingershuttle.com.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.sun.fingershuttle.R
import com.example.sun.fingershuttle.SettingActivity
import kotlinx.android.synthetic.main.fragment_settings.*


class Fragment_Settings : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
                Fragment_Settings()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        finger_print.setOnClickListener {
            val intent = Intent(activity, SettingActivity::class.java)
            intent.putExtra("name", "fingerprint")
            startActivity(intent)
        }
        acount_setting.setOnClickListener {
            val intent = Intent(activity, SettingActivity::class.java)
            intent.putExtra("name", "acount")
            startActivity(intent)
        }
        help_setting.setOnClickListener {
            val intent = Intent(activity, SettingActivity::class.java)
            intent.putExtra("name", "help")
            startActivity(intent)
        }
        connect_setting.setOnClickListener {
            val intent = Intent(activity, SettingActivity::class.java)
            intent.putExtra("name", "connect")
            startActivity(intent)
        }
        display_sound.setOnClickListener {
            val intent = Intent(activity, SettingActivity::class.java)
            intent.putExtra("name", "display_and_sound")
            startActivity(intent)
        }
        product_detection.setOnClickListener {
            val intent = Intent(activity, SettingActivity::class.java)
            intent.putExtra("name", "product_detection")
            startActivity(intent)
        }
        about_fingershuttle.setOnClickListener {
            val intent = Intent(activity, SettingActivity::class.java)
            intent.putExtra("name", "about")
            startActivity(intent)
        }
    }
}
