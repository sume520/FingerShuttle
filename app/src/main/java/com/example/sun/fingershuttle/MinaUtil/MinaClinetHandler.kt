package com.example.sun.fingershuttle.MinaUtil

import org.apache.mina.core.session.IoSession
import org.apache.mina.core.session.IdleStatus
import org.apache.mina.core.service.IoHandlerAdapter


class MinaClientHandler : IoHandlerAdapter() {

    @Throws(Exception::class)
    override fun exceptionCaught(session: IoSession?, cause: Throwable) {
        super.exceptionCaught(session, cause)
        println("客服端与服务器连接发生异常")
    }

    @Throws(Exception::class)
    override fun inputClosed(session: IoSession) {
        super.inputClosed(session)
    }

    @Throws(Exception::class)
    override fun messageReceived(session: IoSession?, message: Any?) {
        super.messageReceived(session, message)
        println("接收到服务器发送的消息为：" + message!!.toString())
    }

    @Throws(Exception::class)
    override fun messageSent(session: IoSession?, message: Any?) {
        super.messageSent(session, message)
    }

    @Throws(Exception::class)
    override fun sessionClosed(session: IoSession?) {
        super.sessionClosed(session)
        println("与服务器的连接已关闭")
    }

    @Throws(Exception::class)
    override fun sessionCreated(session: IoSession?) {
        super.sessionCreated(session)
        println("与服务器的连接已创建")
    }

    @Throws(Exception::class)
    override fun sessionIdle(session: IoSession?, status: IdleStatus?) {
        super.sessionIdle(session, status)
        println("客户端空闲中")
    }

    @Throws(Exception::class)
    override fun sessionOpened(session: IoSession?) {
        super.sessionOpened(session)
        println("与服务器的连接已打开")
    }

}
