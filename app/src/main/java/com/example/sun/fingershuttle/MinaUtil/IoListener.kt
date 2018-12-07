package com.example.sun.fingershuttle.MinaUtil

import android.util.Log
import org.apache.mina.core.service.IoService
import org.apache.mina.core.service.IoServiceListener
import org.apache.mina.core.session.IdleStatus
import org.apache.mina.core.session.IoSession

open class IoListener : IoServiceListener {

    @Throws(Exception::class)
    override fun serviceActivated(arg0: IoService) {
        // TODO Auto-generated method stub

    }

    @Throws(Exception::class)
    override fun serviceDeactivated(arg0: IoService) {
        // TODO Auto-generated method stub

    }

    @Throws(Exception::class)
    override fun serviceIdle(arg0: IoService, arg1: IdleStatus) {
        Log.d("IoListener","服务器空闲中...")
    }

    @Throws(Exception::class)
    override fun sessionClosed(arg0: IoSession) {
        // TODO Auto-generated method stub

    }

    @Throws(Exception::class)
    override fun sessionCreated(arg0: IoSession) {
        // TODO Auto-generated method stub

    }

    @Throws(Exception::class)
    override fun sessionDestroyed(arg0: IoSession) {

    }

}
