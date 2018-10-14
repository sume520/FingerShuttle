package com.example.sun.fingershuttle.com.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.sun.fingershuttle.DBTable.LoginedInfo
import com.example.sun.fingershuttle.Login

import com.example.sun.fingershuttle.R
import com.example.sun.fingershuttle.UserActivity
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_user.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.toast
import org.litepal.LitePal
import org.litepal.extension.findAll

class LoginFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.d("Login", "login")
        // Inflate the layout for this fragment
        isLogined()
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                LoginFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var name: String
        var pwd: String
        login_bt_login.setOnClickListener {
            name = et_acount.text.toString()
            pwd = et_password.text.toString()
            if (name.isBlank()) {
                et_acount.text.clear()
                et_password.text.clear()
                toast("请输入账号")
            } else if (pwd.isBlank()) {
                et_password.text.clear()
                toast("请输入密码,密码不能为空")
            } else if (Login.checkPwd(name, pwd) == true) {
                toast("登录成功")
                val intent = Intent(activity, UserActivity::class.java)
                intent.putExtra("name", "show_user_info")
                startActivity(intent)
                activity!!.finish()
            } else {
                et_password.text.clear()
                toast("登录失败，请检查账号或密码")
            }
        }
    }

    private fun isLogined() {
        if (Login.isLogined()) {
            toast("您已经登录")
            activity!!.finish()
        }
    }
}
