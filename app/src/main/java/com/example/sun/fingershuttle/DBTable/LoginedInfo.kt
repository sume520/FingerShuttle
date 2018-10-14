package com.example.sun.fingershuttle.DBTable

import com.amap.api.navi.enums.CarEnterCameraStatus
import org.litepal.LitePal
import org.litepal.crud.LitePalSupport

data class LoginedInfo(
        var status: Boolean,
        var name: String,
        var sex: Char,
        var phonenumber: String,
        var pwd: String
) : LitePalSupport()