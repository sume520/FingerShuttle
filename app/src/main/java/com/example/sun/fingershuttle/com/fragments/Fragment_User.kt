package com.example.sun.fingershuttle.com.fragments

import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sun.fingershuttle.login.Login

import com.example.sun.fingershuttle.R
import com.example.sun.fingershuttle.UserActivity
import kotlinx.android.synthetic.main.fragment_user.*
import org.jetbrains.anko.support.v4.toast

class Fragment_User : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                Fragment_User()
    }

    private fun isLogined() {
        if (Login.isLogined()) {
            bt_login.visibility = View.GONE
            bt_logout.visibility = View.VISIBLE
        } else {
            bt_login.visibility = View.VISIBLE
            bt_logout.visibility = View.GONE
        }
        view!!.invalidate()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isLogined()

        bt_login.setOnClickListener {
            val intent = Intent(activity, UserActivity::class.java)
            intent.putExtra("name", "login")
            startActivity(intent)
        }
        bt_register.setOnClickListener {
            val intent = Intent(activity, UserActivity::class.java)
            intent.putExtra("name", "register")
            startActivity(intent)
        }
        bt_show_user_info.setOnClickListener {
            val intent = Intent(activity, UserActivity::class.java)
            intent.putExtra("name", "show_user_info")
            startActivity(intent)
        }
        bt_total_ditance.setOnClickListener {
            val intent = Intent(activity, UserActivity::class.java)
            intent.putExtra("name", "total_distance")
            startActivity(intent)
        }
        bt_find_path.setOnClickListener {
            val intent = Intent(activity, UserActivity::class.java)
            intent.putExtra("name", "find_distance")
            startActivity(intent)
        }
        bt_note.setOnClickListener {
            val intent = Intent(activity, UserActivity::class.java)
            intent.putExtra("name", "note")
            startActivity(intent)
        }
        bt_user_current_location.setOnClickListener {
            val intent = Intent(activity, UserActivity::class.java)
            intent.putExtra("name", "user_location")
            startActivity(intent)
        }
        bt_bike_current_location.setOnClickListener {
            val intent = Intent(activity, UserActivity::class.java)
            intent.putExtra("name", "bike_location")
            startActivity(intent)
        }
        bt_navigation.setOnClickListener {
            val intent = Intent(activity, UserActivity::class.java)
            intent.putExtra("name", "navigation")
            startActivity(intent)
        }
        bt_repair_update.setOnClickListener {
            val intent = Intent(activity, UserActivity::class.java)
            intent.putExtra("name", "repair_update")
            startActivity(intent)
        }
        bt_logout.setOnClickListener {
            AlertDialog.Builder(context)
                    .setMessage("确定退出账号吗？")
                    .setTitle("注销")
                    .setPositiveButton("确定") { _, _ ->
                        if (Login.logout()) {
                            toast("注销成功")
                            view!!.invalidate()
                        } else toast("注销失败")
                        view!!.invalidate()
                    }
                    .setNegativeButton("取消", null)
                    .create()
                    .show()
        }
    }

    private class MyBroadcastReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {

        }

    }

    override fun onResume() {
        super.onResume()
        //view!!.invalidate()
    }
}
