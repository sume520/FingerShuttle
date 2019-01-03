package com.example.sun.fingershuttle.com.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sun.fingershuttle.DBTable.LoginedInfo
import com.example.sun.fingershuttle.login.Login

import com.example.sun.fingershuttle.R
import kotlinx.android.synthetic.main.fragment_show_user_info.*
import org.litepal.LitePal
import org.litepal.extension.findAll

class ShowUserInfoFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_user_info, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
                ShowUserInfoFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        updateData()
    }

    private fun updateData() {
        if (Login.isLogined()) {
            var userinfo = LitePal.findAll<LoginedInfo>().first()
            tv_username.text = userinfo.name
            tv_sex.text = userinfo.sex.toString()
            tv_phonenumber.text = userinfo.phonenumber
        } else {
            tv_username.text = ""
            tv_sex.text = ""
            tv_phonenumber.text = ""
        }
    }
}
