package com.example.sun.fingershuttle.MinaUtil

import com.example.sun.fingershuttle.events.AlertEvent
import org.greenrobot.eventbus.EventBus

object MessageUtil {
    private lateinit var args: List<String>
    fun messageParse(message: String): Int {
        args = message.trim().split("#")
        when (args[0]) {
            "0" -> return 0
            "1" -> {
                println("指令部分：" + args[1])
                orderHandle(args[1])
                return 1
            }
            "2" -> return 2

            else -> return -1
        }
    }

    fun getTypeName(type: Int): String {
        when (type) {
            0 -> return "文本"
            1 -> return "指令"
            2 -> return "操作"

            else -> return "未定义"
        }
    }

    private fun orderHandle(order: String) {
        when (order) {
            "alert" -> {
                EventBus.getDefault().post(AlertEvent(args[2]))
            }
            else -> println("指令未定义")
        }
    }
}
