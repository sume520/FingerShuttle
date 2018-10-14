package com.example.sun.fingershuttle

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.sun.fingershuttle.com.fragments.*

class UserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        val intent = intent
        var name = intent.getStringExtra("name")
        replaceFragment(name)
    }

    private fun replaceFragment(name: String) {
        var fragment: Fragment? = null
        when (name) {
            "login" -> fragment = LoginFragment.newInstance()
            "register" -> fragment = RegisterFragment.newInstance()
            "show_user_info" -> fragment = ShowUserInfoFragment.newInstance()
            "total_distance" -> fragment = TotalDistanceFragment.newInstance()
            "find_distance" -> fragment = FindDistanceFragment.newInstance()
            "note" -> fragment = NoteFragment.newInstance()
            "user_location" -> fragment = UserLocationFragment.newInstance()
            "bike_location" -> fragment = BikeLocationFragment.newInstance()
            "navigation" -> fragment = NavigationFragment.newInstance()
            "repair_update" -> fragment = RepairUpdateFragment.newInstance()
        }
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        if (fragment != null) {
            fragmentTransaction.replace(R.id.user_fragment, fragment)
        }
        fragmentTransaction.commit()
    }
}
