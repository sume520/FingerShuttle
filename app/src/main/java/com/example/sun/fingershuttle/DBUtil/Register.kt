package com.example.sun.fingershuttle.DBUtil

import android.annotation.SuppressLint
import android.util.Log
import com.example.sun.fingershuttle.DBTable.User
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.litepal.LitePal
import org.litepal.exceptions.LitePalSupportException
import org.litepal.extension.find
import java.net.URI
import java.net.URL

object Register {
    @SuppressLint("CheckResult")
    fun register(account: String, password:String, name:String, sex: Char, phonenumber: String): Boolean {
        if (!isUserExisted(name)) {
            var rs=false
            //var user = User(account, password,name,sex,phonenumber)
            val URL= "http://120.78.159.172:8008/Login_RegServer/Register?" +
                            "account=$account&&password=$password&&name=$name&&sex=$sex&&phonenumber=$phonenumber"
            //user.save()
            Observable.fromCallable {
                return@fromCallable URL(URL).readText()
            }.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        if("false"==it) rs=false
                        if ("true"==it) rs=true
                    }

            Log.i("====================", "$rs")
            return rs
        }
        return false
    }

    private fun isUserExisted(name: String): Boolean {
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