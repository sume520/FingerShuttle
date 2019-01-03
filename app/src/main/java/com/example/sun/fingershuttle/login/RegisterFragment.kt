package com.example.sun.fingershuttle.login


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sun.fingershuttle.DBUtil.Register

import com.example.sun.fingershuttle.R
import kotlinx.android.synthetic.main.fragment_register.*
import org.jetbrains.anko.support.v4.toast

class RegisterFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }


    companion object {
        @JvmStatic
        fun newInstance() =
                RegisterFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

       /* register_bt_reg.setOnClickListener {
            var name = register_et_acount.text.toString()
            var pwd = register_et_password.text.toString()
            var rePwd = register_repeat_password.text.toString()
            var sex: Char

            if (rb_man.isChecked)
                sex = '男'
            else sex = '女'
            var phonenumber = register_phone.text.toString()
            if (name.isBlank()) {
                toast("用户名不能为空")
            } else if (pwd.isBlank()) {
                toast("密码不能为空")
            } else if (pwd != rePwd) {
                toast("输入密码不匹配")
                register_et_password.text.clear()
                register_repeat_password.text.clear()
            } else {
                if (Register.register(name, sex, phonenumber, pwd)) {
                    toast("注册成功")
                    register_et_acount.text.clear()
                    register_et_password.text.clear()
                    register_repeat_password.text.clear()
                    register_phone.text.clear()
                    rb_man.isChecked = false
                    rb_woman.isChecked = false
                } else toast("注册失败,账号已存在")
            }
        }*/
    }
}
