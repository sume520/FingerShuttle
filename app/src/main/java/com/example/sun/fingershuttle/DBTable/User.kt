package com.example.sun.fingershuttle.DBTable

import org.litepal.annotation.Column
import org.litepal.crud.LitePalSupport

data class User(
        @Column(unique = true, defaultValue = "unknown")
        var name: String,
        var sex: Char,
        var phonenumber: String,
        var pwd: String
) : LitePalSupport()