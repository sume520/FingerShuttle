package com.example.sun.fingershuttle

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.sun.fingershuttle.com.fragments.Fragment_CustomSevice
import com.example.sun.fingershuttle.com.fragments.Fragment_Emergency_handling
import com.example.sun.fingershuttle.com.fragments.Fragment_Security

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
            "fingerprint"->fragment= Fragment_Security.newInstance()
            "acount_inform_security"->fragment= Fragment_CustomSevice.newInstance()
            "help"->fragment= Fragment_Emergency_handling.newInstance()
            "connect" -> null
            "display_and_sound"->null
            "product_detection"->null
            "about"->null
        }
        val fragmentManager=supportFragmentManager
        val fragmentTransaction=fragmentManager.beginTransaction()
        if (fragment!=null) {
            fragmentTransaction.replace(R.id.help_frag,fragment)
        }
        fragmentTransaction.commit()
    }
}
