package com.example.sun.fingershuttle.DBTable

import org.litepal.crud.LitePalSupport

data class CarState(
        var isRing:Boolean=false,
        var isAlert:Boolean=false,
        var isResist:Boolean=false,
        var isOpFprint:Boolean=false,
        var isOpPro:Boolean=false,
        var isAddFPrint:Boolean=false,
        var isOpLight:Boolean=false,
        var GPSState:Int=0,
        var GSMState:Int=0,
        var Power:Int=0
):LitePalSupport()