package com.example.sun.fingershuttle

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.transition.FragmentTransitionSupport
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.sun.fingershuttle.DBTable.User
import com.example.sun.fingershuttle.MinaUtil.ConnectUtil
import com.example.sun.fingershuttle.R.id.menu_message
import com.example.sun.fingershuttle.com.fragments.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.toast
import org.litepal.LitePal
import org.litepal.extension.find


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
        var db = LitePal.getDatabase()
        Log.d("MainAcrivity", db.isDatabaseIntegrityOk.toString())
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
                var intent= Intent(this,SettingActivity::class.java)
                intent.putExtra("name","message")
                startActivity(intent)
            }
        }

        return true
    }

    private fun setupViewPager(viewPager: ViewPager) {
        var adapter = ViewPagerAdapter(supportFragmentManager)

        adapter.addFragment(Fragment_Home.newInstance())
        adapter.addFragment(Fragment_Monitor.newInstance())
        adapter.addFragment(Fragment_User.newInstance())
        adapter.addFragment(Fragment_Help.newInstance())
        adapter.addFragment(Fragment_Settings.newInstance())
        viewPager.adapter = adapter
    }
}
