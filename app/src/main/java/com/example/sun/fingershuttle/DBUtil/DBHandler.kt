package com.example.sun.fingershuttle.DBUtil

import java.sql.ResultSet

object DBHandler {

    fun query(sql: String): ResultSet? {
        var rs: ResultSet?
        rs = DBUtil.execQuerySql(sql)
        return rs
    }

    fun insert(sql: String) {
        if (DBUtil.execSql(sql)) {
            println("数据库添加数据成功！")
        } else
            println("数据库添加数据失败...")
    }

    fun delete(sql: String) {
        if (DBUtil.execSql(sql)) {
            println("数据库删除数据成功！")
        } else
            println("数据库删除数据失败...")
    }

    fun update(sql: String) {
        if (DBUtil.execSql(sql)) {
            println("数据库更新数据成功！")
        } else
            println("数据库更新数据失败...")
    }
}
