package com.example.sun.fingershuttle

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.sun.fingershuttle.com.fragments.Fragment_CustomSevice
import com.example.sun.fingershuttle.com.fragments.Fragment_Emergency_handling
import com.example.sun.fingershuttle.com.fragments.Fragment_Security

class HelppingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_helpping)

        val intent=intent
        var name=intent.getStringExtra("name")
        replaceFragment(name)
    }

    private fun replaceFragment(name:String){
        var fragment:Fragment?=null
        when(name){
            "security_center"->fragment=Fragment_Security.newInstance()
            "custom_service"->fragment=Fragment_CustomSevice.newInstance()
            "urgency"->fragment=Fragment_Emergency_handling.newInstance()
        }
        val fragmentManager=supportFragmentManager
        val fragmentTransaction=fragmentManager.beginTransaction()
        if (fragment!=null) {
            fragmentTransaction.replace(R.id.help_frag,fragment)
        }
        fragmentTransaction.commit()
    }
}
