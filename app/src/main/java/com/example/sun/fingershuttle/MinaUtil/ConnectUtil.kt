package com.example.sun.fingershuttle.MinaUtil

import android.os.Handler
import android.util.Log
import org.apache.mina.core.future.ConnectFuture
import org.apache.mina.filter.codec.ProtocolCodecFilter
import org.apache.mina.transport.socket.nio.NioSocketConnector
import rx.*
import java.net.InetSocketAddress
import org.apache.mina.filter.codec.textline.LineDelimiter
import org.apache.mina.filter.codec.textline.TextLineCodecFactory
import java.nio.charset.Charset
import org.apache.mina.core.session.IoSession


object ConnectUtil : Runnable{
    val handler= Handler()
    private lateinit var connector:NioSocketConnector
    private lateinit var address:InetSocketAddress
    private var session: IoSession?=null
    private lateinit var future:ConnectFuture
    private const val IP="120.78.159.172"
    private const val PORT=3344

    override fun run() {
        address= InetSocketAddress(IP,PORT)
        connector= NioSocketConnector()
        connector.filterChain.addLast("codec",
                ProtocolCodecFilter(TextLineCodecFactory(Charset.forName("UTF-8"),
                        LineDelimiter.WINDOWS.value, LineDelimiter.WINDOWS.value)))
        connector.connectTimeoutCheckInterval=10000
        connector.handler=MinaClientHandler()
        connector.setDefaultRemoteAddress(address)
        connector.addListener(object : IoListener() {//监听连接状态，断线 重连

            @Throws(Exception::class)
            override fun sessionDestroyed(arg0: IoSession) {
                super.sessionDestroyed(arg0)
                Log.d("ConnectUtil","连接断开，尝试重连...")
                try {
                    val failCount = 0
                    while (true) {
                        Thread.sleep(5000)
                        println((connector
                                .defaultRemoteAddress as InetSocketAddress)
                                .address
                                .hostAddress)
                        future = connector.connect()
                        future.awaitUninterruptibly()
                        session = future.session
                        if (session != null && session!!.isConnected()) {
                            println("断线重连["
                                    + (session!!.getRemoteAddress() as InetSocketAddress).address.hostAddress
                                    + ":" + (session!!.getRemoteAddress() as InetSocketAddress).port + "]成功")
                            session!!.write("start")
                            break
                        } else {
                            println("断线重连失败--->" + failCount + "次")
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

        })
        Log.d("connect","准备连接服务器")
        try {
            future = connector.connect()
            future.awaitUninterruptibly()
            session = future.session
            handler.post {
                SessionManager.session=session
                SessionManager.writeMsg("start")
            }
            Log.e("ConnectUtil","连接服务器成功")
        }catch (e:Exception){
            Log.e("ConnectUtil","连接服务器失败")
        }



    }

    fun connect() {
        Thread(this).start()
    }

}