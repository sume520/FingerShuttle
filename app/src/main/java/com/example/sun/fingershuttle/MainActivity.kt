package com.example.sun.fingershuttle

import android.Manifest
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.NotificationCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.sun.fingershuttle.MinaUtil.ConnectUtil
import com.example.sun.fingershuttle.R.id.menu_message
import com.example.sun.fingershuttle.com.fragments.*
import com.example.sun.fingershuttle.events.AlertEvent
import kotlinx.android.synthetic.main.fragment_home.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.toast
import org.litepal.LitePal
import pub.devrel.easypermissions.EasyPermissions

private const val PERMISSION_REQUEST_READ_WRITE_EXTERNAL_STORAGE = 1

class MainActivity : AppCompatActivity(), View.OnClickListener {


    private lateinit var navi: BottomNavigationView
    private lateinit var toolbar: Toolbar
    private lateinit var viewPager: ViewPager
    private var is_ring_on = false
    private var is_lock_on = false
    private var is_light_on = false
    private var is_resist_on = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val db = LitePal.getDatabase()
        Log.d("MainAcrivity", db.isDatabaseIntegrityOk.toString())

        //申请权限
        EasyPermissions.requestPermissions(
                this@MainActivity,
                "没有相应权限软件将无法工作",
                PERMISSION_REQUEST_READ_WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
        )
        init()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_ring -> {
                if (!is_ring_on) {
                    btn_ring.imageResource = R.drawable.ic_buzz_on_24dp
                    is_ring_on = true
                    toast("开启鸣笛")
                } else {
                    btn_ring.imageResource = R.drawable.ic_buzz_off_24dp
                    is_ring_on = false
                    toast("关闭鸣笛")
                }
            }
            R.id.btn_lock -> {
                if (!is_lock_on) {
                    btn_lock.imageResource = R.drawable.ic_lock_on_24dp
                    is_lock_on = true
                    toast("上锁")
                } else {
                    btn_lock.imageResource = R.drawable.ic_lock_off_24dp
                    is_lock_on = false
                    toast("开锁")
                }
            }
            R.id.btn_light -> {
                if (!is_light_on) {
                    btn_light.imageResource = R.drawable.ic_light_on_24dp
                    is_light_on = true
                    toast("打开车灯")
                } else {
                    btn_light.imageResource = R.drawable.ic_light_off_24dp
                    is_light_on = false
                    toast("关闭车灯")
                }
            }
            R.id.btn_resist -> {
                if (!is_resist_on) {
                    btn_resist.imageResource = R.drawable.ic_resist_on_24dp
                    is_resist_on = true
                    toast("打开自动抗拒")
                } else {
                    btn_resist.imageResource = R.drawable.ic_resist_off_24dp
                    is_resist_on = false
                    toast("关闭自动抗拒")
                }
            }
        }
    }

    @SuppressLint("ObsoleteSdkInt")
    private fun init() {
        //实现沉浸效果
        if (Build.VERSION.SDK_INT >= 21) {
            val decorView = window.decorView
            val option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            decorView.systemUiVisibility = option
            window.statusBarColor = Color.TRANSPARENT
        }
        setViewPager()
        //连接服务器
        ConnectUtil.connect()

        //创建通知渠道
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "alert"
            val channelName = "警报"
            val importance = NotificationManager.IMPORTANCE_HIGH
            createNotificationChannel(channelId, channelName, importance)
        }
    }


    private fun setViewPager(){
        viewPager = findViewById(R.id.viewpager)
        navi = findViewById(R.id.btn_navi_view)
        navi.setOnNavigationItemSelectedListener(object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.item_home -> viewPager.setCurrentItem(0)
                    R.id.item_monitor -> viewPager.setCurrentItem(1)
                    R.id.item_user -> viewPager.setCurrentItem(2)
                    R.id.item_help -> viewPager.setCurrentItem(3)
                    R.id.item_setting -> viewPager.setCurrentItem(4)
                }
                return false
            }
        })
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        navi.menu.getItem(0).setChecked(true)
                        toolbar.setTitle("指梭")
                        toolbar.setTitleTextColor(Color.WHITE)
                        window.statusBarColor = Color.TRANSPARENT
                    }
                    1 -> {
                        navi.menu.getItem(1).setChecked(true)
                        toolbar.setTitle("实时监控")
                        toolbar.setTitleTextColor(Color.WHITE)
                        //window.statusBarColor = 0x62ffffff
                        window.statusBarColor = Color.TRANSPARENT
                    }
                    2 -> {
                        navi.menu.getItem(2).setChecked(true)
                        toolbar.title = "用户"
                        toolbar.setTitleTextColor(Color.WHITE)
                        window.statusBarColor = Color.TRANSPARENT
                    }
                    3 -> {
                        navi.menu.getItem(3).setChecked(true)
                        toolbar.title = "帮助"
                        toolbar.setTitleTextColor(Color.WHITE)
                        window.statusBarColor = Color.TRANSPARENT
                    }
                    4 -> {
                        navi.menu.getItem(4).setChecked(true)
                        toolbar.title = "设置"
                        toolbar.setTitleTextColor(Color.WHITE)
                        window.statusBarColor = Color.TRANSPARENT
                    }
                }
                invalidateOptionsMenu()
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

        })
        setupViewPager(viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        when (viewPager.currentItem) {
            0 -> {
                menu!!.findItem(menu_message).setVisible(true)
            }
            1 -> {
                menu!!.findItem(menu_message).setVisible(false)
            }
            2 -> {
                menu!!.findItem(menu_message).setVisible(false)
            }
            3 -> {
                menu!!.findItem(menu_message).setVisible(false)
            }
            4 -> {
                menu!!.findItem(menu_message).setVisible(false)
            }
        }

        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.menu_message -> {
                val intent= Intent(this,SettingActivity::class.java)
                intent.putExtra("name","message")
                startActivity(intent)
            }
        }

        return true
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)

        adapter.addFragment(Fragment_Home.newInstance())
        adapter.addFragment(Fragment_Monitor.newInstance())
        adapter.addFragment(Fragment_User.newInstance())
        adapter.addFragment(Fragment_Help.newInstance())
        adapter.addFragment(Fragment_Settings.newInstance())
        viewPager.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun newAlert(event: AlertEvent) {
        val manager =
                getSystemService(Context.NOTIFICATION_SERVICE)
                        as NotificationManager
        val resultIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent
                .getActivity(
                        this, 0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT)
        val notification = NotificationCompat.Builder(this, "alert")
                .setContentTitle("警报")
                .setContentText("警报信息：${event.message}")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(false)
                .setSmallIcon(R.drawable.ic_alert)
                .setContentIntent(pendingIntent)
                .setLargeIcon(
                        BitmapFactory
                                .decodeResource(
                                        resources, R.drawable.ic_alert))
                .build()
        //持续警报
        notification.flags = Notification.FLAG_AUTO_CANCEL
        manager.notify(1, notification)
    }

    @TargetApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(
            channelId: String,
            channelName: String,
            importance: Int
    ) {//创建通知渠道
        var channel = NotificationChannel(channelId, channelName, importance)
        var notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE)
                        as NotificationManager
        notificationManager.createNotificationChannel(channel)

    }

    //权限申请回调
    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        if (requestCode == PERMISSION_REQUEST_READ_WRITE_EXTERNAL_STORAGE) {
            if (grantResults.size > 0) {
                println(grantResults.size)
                for (res in grantResults) {
                    if (res == PackageManager.PERMISSION_GRANTED)
                        Toast.makeText(this, "获取到权限", Toast.LENGTH_SHORT).show()
                    else {
                        Toast.makeText(this, "获取权限被拒绝，重启软件后可重新获取", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            return
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

}
