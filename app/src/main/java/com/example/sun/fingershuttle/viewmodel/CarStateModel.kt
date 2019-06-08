package com.example.sun.fingershuttle.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.sun.fingershuttle.utils.secToTime

class CarStateModel : ViewModel() {
    var isRing = MutableLiveData<Boolean>()
    var isAlert = MutableLiveData<Boolean>()
    var isResist = MutableLiveData<Boolean>()
    var isOpFprint = MutableLiveData<Boolean>()
    var isOpPro = MutableLiveData<Boolean>()
    var isAddFPrint = MutableLiveData<Boolean>()
    var isOpLight = MutableLiveData<Boolean>()
    var GPSState = MutableLiveData<Int>()
    var GSMState = MutableLiveData<Int>()
    var driveTime = MutableLiveData<String>()
        get() {
            field.value = secToTime(power.value!! * 200)
            return field
        }
    var power = MutableLiveData<Int>()

    fun culDriveTime(time: Int): String {
        return secToTime(time * 200)
    }
}