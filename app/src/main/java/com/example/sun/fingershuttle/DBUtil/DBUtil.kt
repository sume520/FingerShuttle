package com.example.sun.fingershuttle.DBUtil

import android.util.Log
import java.sql.ResultSet
import java.sql.SQLException

import com.mysql.jdbc.Connection
import com.mysql.jdbc.Statement
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import kotlin.concurrent.thread

object DBUtil {
    private var con: Connection? = null
    private val username = "root"
    private val pwd = "Root_2018"
    private val database = "inno"
    private var stmt: Statement? = null

//    init {
//        con=DBConnect.getConnection(username,pwd, database)
//    }

    internal fun execQuerySql(sql: String): ResultSet? {
        val rs: ResultSet? = null
        var stmt: Statement? = null

        try {
            if (con == null || con!!.isClosed) {
                con = DBConnect.getConnection(username, pwd, database)
//                var task=Task()
//                var threadPool= Executors.newCachedThreadPool()
//                var future=threadPool.submit(task)
//                con=future.get()
                Log.d("DBUtil", "获取数据库连接完成")
            }
            stmt = con!!.createStatement() as Statement
            Log.w("", "100")
            stmt.executeQuery(sql)
            Log.w("", "101")
        } catch (e: SQLException) {
            // TODO Auto-generated catch block
            //e.printStackTrace()
            Log.e("DBUtil", "数据库查询异常")
        }

        return rs
    }

    internal fun execSql(sql: String): Boolean {
        stmt = null
        try {
            if (con == null || con!!.isClosed) {
                con = DBConnect.getConnection(username, pwd, database)
            }
            return stmt!!.execute(sql)
        } catch (e: SQLException) {
            //e.printStackTrace()
            Log.e("DBUtil", "数据库操作异常")
        }

        return false
    }

    class Task : Callable<Connection> {
        override fun call(): Connection? {
            return DBConnect.getConnection(username, pwd, database)
        }
    }
}
