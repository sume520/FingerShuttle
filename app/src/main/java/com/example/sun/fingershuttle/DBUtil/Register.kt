package com.example.sun.fingershuttle.DBUtil

import android.util.Log
import com.example.sun.fingershuttle.DBTable.User
import org.litepal.LitePal
import org.litepal.exceptions.LitePalSupportException
import org.litepal.extension.find

object Register {
    fun register(name: String, sex: Char, phonenumber: String, pwd: String): Boolean {
        if (!isUserExisted(name)) {
            var user = User(name, sex, phonenumber, pwd)
            user.save()
            Log.d("register", "注册成功")
            return true
        }
        return false
    }

    fun isUserExisted(name: String): Boolean {
        try {
            if (LitePal.where("name like ?", name).find<User>().isEmpty()) {
                Log.d("isUserExisted", "账号不存在")
                return false
            } else Log.d("isUserExisted", "账号已存在")
        } catch (e: LitePalSupportException) {
            Log.e("isUserExisted", "查询异常")
        }

        return true
    }
}