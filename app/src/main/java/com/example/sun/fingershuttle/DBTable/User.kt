package com.example.sun.fingershuttle.DBTable

import org.litepal.annotation.Column
import org.litepal.crud.LitePalSupport

data class User(
        @Column(unique = true, defaultValue = "unknown")
        var account:String,
        var password: String,
        var name: String,
        var sex: Char,
        var phonenumber: String
        ) : LitePalSupport(){
    override fun toString(): String {
        println("name ")
        return super.toString()
    }
}