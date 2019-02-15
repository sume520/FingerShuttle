package com.example.sun.fingershuttle.MinaUtil

import android.util.Log
import org.apache.mina.core.session.IoSession
import java.io.IOException

object SessionManager {
    var session: IoSession? = null

    fun writeMsg(msg:String){
        if(session!=null && session!!.isConnected){
            try{
                session!!.write(msg)
                Log.i("==========", "发送数据： $msg")
            }catch (e:IOException){
                Log.d("writeMsg","发送数据异常")
            }
        }
    }

}