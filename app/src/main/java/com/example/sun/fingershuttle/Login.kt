package com.example.sun.fingershuttle

import android.util.Log
import android.view.View
import com.example.sun.fingershuttle.DBTable.LoginedInfo
import com.example.sun.fingershuttle.DBTable.User
import com.example.sun.fingershuttle.DBUtil.DBHandler
import kotlinx.android.synthetic.main.fragment_user.*
import kotlinx.coroutines.experimental.selects.select
import org.litepal.LitePal
import org.litepal.exceptions.LitePalSupportException
import org.litepal.extension.deleteAll
import org.litepal.extension.find
import org.litepal.extension.findAll
import org.litepal.extension.findFirst
import java.sql.ResultSet
import kotlin.math.log

object Login {
    private var rs: ResultSet? = null

    fun checkPwd(name: String, pwd: String): Boolean {
        var db = LitePal.getDatabase()
        var user: User?
        var users = LitePal.where("name like ?", name).find<User>()
        if (users.isEmpty())
            return false
        user = users[0]
        if (user.pwd == pwd) {
            var loginedInfo = LitePal.findFirst<LoginedInfo>()
            if (loginedInfo != null)
                LitePal.deleteAll<LoginedInfo>()
            loginedInfo = LoginedInfo(
                    true, user.name, user.sex, user.phonenumber, user.pwd)
            loginedInfo.save()

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
            var lfs: List<LoginedInfo>
            if (!LitePal.findAll<LoginedInfo>().isEmpty()) {
                lfs = LitePal.findAll()
                var lf = lfs[0]
                return lf.status == true
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
}