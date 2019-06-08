package com.example.sun.fingershuttle.login

import android.os.Handler
import android.util.Log
import android.widget.Toast
import com.example.sun.fingershuttle.DBTable.LoginedInfo
import com.example.sun.fingershuttle.DBTable.User
import com.google.gson.Gson
import okhttp3.*
import org.litepal.LitePal
import org.litepal.LitePalApplication
import org.litepal.exceptions.LitePalSupportException
import org.litepal.extension.deleteAll
import org.litepal.extension.findAll
import org.litepal.extension.findFirst
import java.io.IOException

object Login {
    private var json = ""

    fun checkAccountAndPwd(account: String, pwd: String): Boolean {
        val user: User?
        val url = "http://120.78.159.172:8008/Login_RegServer/Login"
        val client = OkHttpClient()
        val body = FormBody.Builder()
                .add("account", "sume520")
                .add("password", "abc123")
                .build()
        val request = Request.Builder()
                .url(url)
                .post(body)
                .build()
        val call = client.newCall(request)
        call.enqueue(object : okhttp3.Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("获取数据出错")
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val msg = handler.obtainMessage()
                //msg.what = 0x123
                msg.obj = response.body()!!.string()
                println("接收数据：${msg.obj}")
                handler.sendMessage(msg)

                handler.post {
                    //json=msg.obj as String
                    Toast.makeText(LitePalApplication.sContext, json, Toast.LENGTH_SHORT).show()
                }
            }
        })
        println("json: $json")
        if (json == "") {
            Toast.makeText(LitePalApplication.sContext, "登录错误", Toast.LENGTH_SHORT).show()
            Log.e("Login Error", "获取数据为空")
            return false
        }
        if (json == "2") {
            Toast.makeText(LitePalApplication.sContext, "账号或密码不正确", Toast.LENGTH_SHORT).show()
            Log.e("Login Error", "账号或密码不正确")
            return false
        }
        val gson = Gson()
        user = gson.fromJson(json, User::class.java)

        if (user.password == pwd) {
            var loginedInfo = LitePal.findFirst<LoginedInfo>()
            if (loginedInfo != null)
                LitePal.deleteAll<LoginedInfo>()
            loginedInfo = LoginedInfo(
                    true, user.name, user.sex, user.phonenumber, user.password)
            loginedInfo.save()
            Log.d("Login Succeed", "登录成功")
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

    fun isLogined(): Boolean {
        try {
            val lfs: List<LoginedInfo>
            if (!LitePal.findAll<LoginedInfo>().isEmpty()) {
                lfs = LitePal.findAll()
                val lf = lfs[0]
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
        try {
            if (!LitePal.findAll<LoginedInfo>().isEmpty()) {
                LitePal.deleteAll<LoginedInfo>()
                return true
            }
        } catch (e: LitePalSupportException) {
            Log.d("Login", "删除表LoginedInfo异常")
        }
        return false
    }

    internal val handler = Handler(Handler.Callback { msg ->
        val message = msg!!.obj as String
        json = message
        Log.d("MyHandler", message)
        Toast.makeText(LitePalApplication.sContext, json, Toast.LENGTH_SHORT).show()

        return@Callback true
    })

}