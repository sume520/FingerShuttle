package com.example.sun.fingershuttle.MinaUtil

import android.os.Handler
import android.util.Log
import org.apache.mina.core.future.ConnectFuture
import org.apache.mina.core.session.IoSession
import org.apache.mina.filter.codec.ProtocolCodecFilter
import org.apache.mina.filter.codec.textline.LineDelimiter
import org.apache.mina.filter.codec.textline.TextLineCodecFactory
import org.apache.mina.transport.socket.nio.NioSocketConnector
import java.net.InetSocketAddress
import java.nio.charset.Charset


object ConnectUtil : Runnable {
    val handler = Handler()
    private lateinit var connector: NioSocketConnector
    private lateinit var address: InetSocketAddress
    private var session: IoSession? = null
    private lateinit var future: ConnectFuture
    private const val IP = "120.78.159.172"
    //private const val IP = "192.168.123.96"
    private const val PORT = 3344

    override fun run() {
        address = InetSocketAddress(IP, PORT)
        connector = NioSocketConnector()
        connector.filterChain.addLast("codec",
                ProtocolCodecFilter(TextLineCodecFactory(Charset.forName("UTF-8"),
                        LineDelimiter.WINDOWS.value, LineDelimiter.WINDOWS.value)))
        connector.connectTimeoutCheckInterval = 10000
        connector.handler = MinaClientHandler()
        connector.setDefaultRemoteAddress(address)
        connector.addListener(object : IoListener() {//监听连接状态，断线 重连
            @Throws(Exception::class)
            override fun sessionDestroyed(arg0: IoSession) {
                super.sessionDestroyed(arg0)
                Log.d("===============", "连接断开，尝试重连...")
            reConnect()
        }

        })
        Log.i("==================", "准备连接服务器")
        try {
            future = connector.connect()
            future.awaitUninterruptibly()
            session = future.session
            handler.post {
                SessionManager.session = session
                SessionManager.writeMsg("0#start")
            }
            Log.i("================", "连接服务器成功")
        } catch (e: Exception) {
            Log.e("================", "连接服务器失败")
        }
    }

    //重连
    fun reConnect() {
        try {
            val failCount = 0
            while (true) {
                //五秒一次重连
                Thread.sleep(5000)
                println("服务器IP：${(connector
                        .defaultRemoteAddress as InetSocketAddress)
                        .address
                        .hostAddress}")
                future = connector.connect()
                future.awaitUninterruptibly()
                session = future.session
                if (session != null && session!!.isConnected()) {
                    println("断线重连["
                            + (session!!.getRemoteAddress() as InetSocketAddress).address.hostAddress
                            + ":" + (session!!.getRemoteAddress() as InetSocketAddress).port + "]成功")
                    //session!!.write("start")
                    handler.post {
                        SessionManager.session = session
                        SessionManager.writeMsg("0#start")
                    }
                    break
                } else {
                    println("断线重连失败--->" + failCount + "次")
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun connect() {
        Thread(this).start()
    }

}