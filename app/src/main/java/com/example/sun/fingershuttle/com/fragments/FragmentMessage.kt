package com.example.sun.fingershuttle.com.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.sun.fingershuttle.MinaUtil.SessionManager
import com.example.sun.fingershuttle.R
import kotlinx.android.synthetic.main.fragment_message.*
import org.jetbrains.anko.support.v4.toast

class FragmentMessage : Fragment() {
    private var msgList= arrayListOf<String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message, container, false)
    }


    companion object {
        @JvmStatic
        fun newInstance() =
                FragmentMessage()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var adapter=ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,msgList)
        list_msg.adapter=adapter

        bt_send.setOnClickListener {
            var msg=et_send_msg.text.toString()
            if(!msg.isBlank()){
                msgList.add("发送: "+msg)
                adapter.notifyDataSetChanged()
                toast(msg)
                et_send_msg.text.clear()
                SessionManager.writeMsg(msg)
            }
        }
    }
}
