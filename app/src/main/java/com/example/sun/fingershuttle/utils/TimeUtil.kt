package com.example.sun.fingershuttle.utils

/**
 *
 * 将秒数转化为时分秒
 * @param time 时间段对应的秒数
 * @return 时分秒格式时间戳
 */
fun secToTime(time: Int): String {
    var time = time
    var hour = 0
    var minute = 0
    var second = 0
    if (time <= 0) {
        return "00时00分"
    } else {
        if (time >= 3600) {
            hour = time / 3600
            time = time % 3600
        }

        if (time >= 60) {
            minute = time / 60
            second = time % 60
        }
        return timeFormat(hour) + "小时" + timeFormat(minute) + "分钟"
    }
}

fun timeFormat(num: Int): String {
    var retStr: String
    if (num >= 0 && num < 10) {
        retStr = "0" + Integer.toString(num)
    } else {
        retStr = "" + num
    }
    return retStr
}