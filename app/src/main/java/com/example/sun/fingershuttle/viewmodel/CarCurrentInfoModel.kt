package com.example.sun.fingershuttle.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class CarCurrentInfoModel : ViewModel() {
    var speed = MutableLiveData<Int>()
    var mileage = MutableLiveData<Int>()
    var power = MutableLiveData<Int>()
}