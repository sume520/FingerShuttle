package com.example.sun.fingershuttle.login


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.sun.fingershuttle.DBTable.LoginedInfo
import com.example.sun.fingershuttle.DBTable.User
import com.example.sun.fingershuttle.MainActivity
import com.example.sun.fingershuttle.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_login.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.support.v4.toast
import org.litepal.LitePal
import org.litepal.LitePalApplication
import org.litepal.exceptions.LitePalSupportException
import org.litepal.extension.deleteAll
import org.litepal.extension.findAll
import org.litepal.extension.findFirst
import java.net.URL

class LoginFragment : Fragment() {

    private var json = ""
    private val handler = Handler(Handler.Callback { msg ->
        var message = msg!!.obj as String
        json = message
        Log.d("MyHandler", json)
        toast(json)
        return@Callback true
    })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.d("Login", "login")
        // Inflate the layout for this fragment

        EventBus.getDefault().register(this)
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                LoginFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (isLogined()) {//判断是否登录，若已经登录则跳过登录界面
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
            activity!!.finish()
        }
        var account: String
        var password: String
        login_bt_login.setOnClickListener {
            account = et_acount.text.toString()
            password = et_password.text.toString()
            if (isLogined()) {
                toast("已登录")
                activity!!.finish()
            }
            if (account.isBlank()) {
                et_acount.text.clear()
                et_password.text.clear()
                toast("请输入账号")
            } else if (password.isBlank()) {
                et_password.text.clear()
                toast("请输入密码,密码不能为空")
            } /*else if (Login(name, password) == true) {
                toast("登录成功")
                val intent = Intent(activity, UserActivity::class.java)
                intent.putExtra("name", "show_user_info")
                startActivity(intent)
                activity!!.finish()
            } else {
                et_password.text.clear()
                toast("登录失败，请检查账号或密码")
            }*/
            EventBus.getDefault().post(MessageEvent(account, password))
        }
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    fun Login(messageEvent: MessageEvent) {
        var account = messageEvent.account
        var password = messageEvent.password
        var url = "http://120.78.159.172:8008/Login_RegServer/Login?account=$account&password=$password"
        var json = URL(url).readText()

        Log.d("Login", json)
        if (!loginCheck(password, json)) {
            //handler.post {
                toast("登录失败，请检查账号密码")
                et_password.text.clear()
            //}
        } else {
//            handler.post {
                //toast("登录成功")
                //val intent = Intent(activity, UserActivity::class.java)
                //intent.putExtra("name", "show_user_info")
                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
                activity!!.finish()
           // }
        }
    }

    private fun loginCheck(password: String, json: String): Boolean {
        var user: User?
        if (json == "") {
            handler.post {
                Toast.makeText(LitePalApplication.sContext, "登录错误", Toast.LENGTH_SHORT).show()
            }
            Log.e("Login Error", "获取数据为空")
            return false
        }
        if (json == "2") {
            handler.post {
                Toast.makeText(LitePalApplication.sContext, "账号或密码不正确", Toast.LENGTH_SHORT).show()
                et_password.text.clear()
            }
            Log.e("Login Error", "账号或密码不正确")
            return false
        }
        var gson = Gson()
        user = gson.fromJson(json, User::class.java)
        println(json)
        Log.d("loginCheck", user.password)
        println(password)
        if (user.password == password) {
            var loginedInfo = LitePal.findFirst<LoginedInfo>()
            if (loginedInfo != null)
                LitePal.deleteAll<LoginedInfo>()
            loginedInfo = LoginedInfo(
                    true, user.name, user.sex, user.phonenumber, user.password)
            loginedInfo.save()
            //Log.d("Login Succeed", "登录成功")
            return true
        }
        try {
            if (!LitePal.findAll<LoginedInfo>().isEmpty())
                LitePal.deleteAll<LoginedInfo>()
        } catch (e: LitePalSupportException) {
            Log.d("Login", "删除表LoginedInfo异常")
        }
        return false
    }

    private fun isLogined(): Boolean {
        try {
            var lfs: List<LoginedInfo>
            if (!LitePal.findAll<LoginedInfo>().isEmpty()) {
                lfs = LitePal.findAll()
                var lf = lfs[0]
                return lf.status
            }
        } catch (e: LitePalSupportException) {
            Log.d("isLogined", "查询数据库表“LoginedInfo”异常")
        }
        return false
    }

    fun logout(): Boolean {
        if (!isLogined()) {
            Log.d("logout", "账户未登录")
            return false
        }
        try {//注销登录即把登录表中的数据删除
            if (!LitePal.findAll<LoginedInfo>().isEmpty()) {
                LitePal.deleteAll<LoginedInfo>()
                return true
            }
        } catch (e: LitePalSupportException) {
            Log.d("Login", "删除表LoginedInfo异常，注销失败")
        }
        return false
    }

}
