package com.example.sun.fingershuttle

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.sun.fingershuttle.com.fragments.*

class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        val intent=intent
        var name=intent.getStringExtra("name")
        replaceFragment(name)
    }

    private fun replaceFragment(name:String){
        var fragment: Fragment?=null
        when(name){
            "fingerprint" -> fragment = Fragment_FingerPrint.newInstance()
            "acount" -> fragment = FragmentAcount.newInstance()
            "help" -> fragment = FragmentSettingHelp.newInstance()
            "connect" -> fragment = FragmentConnect.newInstance()
            "display_and_sound" -> fragment = FragmentDisplayAndSound.newInstance()
            "product_detection" -> fragment = FragmentProductDetection.newInstance()
            "about" -> fragment = FragmentAbout.newInstance()
        }
        val fragmentManager=supportFragmentManager
        val fragmentTransaction=fragmentManager.beginTransaction()
        if (fragment!=null) {
            fragmentTransaction.replace(R.id.setting_frag, fragment)
        }
        fragmentTransaction.commit()
    }
}
