package com.example.sun.fingershuttle.DBUtil

import android.util.Log
import com.mysql.jdbc.Connection
import java.sql.DriverManager
import java.sql.SQLException

object DBConnect {

    internal fun getConnection(username: String, pwd: String, database: String): Connection? {
        //加载驱动
        try {
            Class.forName("com.mysql.jdbc.Driver")
        } catch (e: Exception) {
            //e.printStackTrace()
            Log.e("DBConnect", "驱动加载异常")
        }

        //连接数据库
        var con: Connection? = null
        val uri = "jdbc:mysql://120.78.159.172:3306/" + database + "?&useSSL=true&characterEncoding=utf-8"

        try {
            con = DriverManager.getConnection(uri, username, pwd) as Connection
        } catch (e1: SQLException) {
            //e1.printStackTrace()
            Log.e("DBConnect", "数据库连接异常")
        }

        return con
    }
}