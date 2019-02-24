package com.example.sun.fingershuttle.com.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.amap.api.maps.AMap
import com.amap.api.maps.CameraUpdateFactory
import com.amap.api.maps.model.LatLng
import com.amap.api.maps.model.MarkerOptions
import com.example.sun.fingershuttle.R
import kotlinx.android.synthetic.main.fragment_user_location.*
import java.text.SimpleDateFormat
import java.util.*


class UserLocationFragment : Fragment() {
    private lateinit var aMap: AMap
    //声明mlocationClient对象
    var mlocationClient: AMapLocationClient? = null
    //声明mLocationOption对象
    var mLocationOption: AMapLocationClientOption? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_location, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        user_map.onCreate(savedInstanceState)

        //初始化定位
        mlocationClient = AMapLocationClient(context);
        //设置定位回调监听
        mlocationClient!!.setLocationListener(mLocationListener);

        //地图设置
        aMap = user_map.map
        setUpMap()

    }

    /**
     * 声明定位回调监听器
     */
    var mLocationListener: AMapLocationListener = AMapLocationListener { amapLocation ->
        if (amapLocation != null) {
            if (amapLocation.errorCode == 0) {
                //定位成功回调信息，设置相关消息
                amapLocation.locationType//获取当前定位结果来源，如网络定位结果，详见定位类型表
                amapLocation.latitude//获取纬度
                amapLocation.longitude//获取经度
                amapLocation.accuracy//获取精度信息
                val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA)
                val date = Date(amapLocation.time)
                df.format(date)//获取定位时间
                amapLocation.address//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                amapLocation.country//国家信息
                amapLocation.province//省信息
                amapLocation.city//城市信息
                amapLocation.district//城区信息
                amapLocation.street//街道信息
                amapLocation.streetNum//街道门牌号信息
                amapLocation.cityCode//城市编码
                amapLocation.adCode//地区编码
                amapLocation.aoiName//获取当前定位点的AOI信息
                val lat = amapLocation.latitude//获取经纬度
                val lon = amapLocation.longitude
                Log.i("==============", "lat : $lat lon : $lon")
                Log.i("==============", "Country : " + amapLocation.country + " province : " + amapLocation.province + " City : " + amapLocation.city + " District : " + amapLocation.district)

                // 设置当前地图显示为当前位置
                aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(lat, lon), 19f))
                val markerOptions = MarkerOptions()
                markerOptions.position(LatLng(lat, lon))
                markerOptions.title("当前位置")
                markerOptions.visible(true)
                /*val bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(resources, R.mipmap.locate))
                markerOptions.icon(bitmapDescriptor)*/
                aMap.clear()
                aMap.addMarker(markerOptions)
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + amapLocation.errorCode + ", errInfo:"
                        + amapLocation.errorInfo)
            }
        }
    }

    private fun setUpMap() {
        //初始化定位参数
        mLocationOption = AMapLocationClientOption()
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption!!.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy)
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption!!.setNeedAddress(true)
        //设置是否只定位一次,默认为false
        mLocationOption!!.setOnceLocation(false)
        //设置是否强制刷新WIFI，默认为强制刷新
        //mLocationOption!!.setWifiActiveScan(true)
        mLocationOption!!.setWifiScan(true)
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption!!.setMockEnable(false)
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption!!.setInterval(2000)
        //给定位客户端对象设置定位参数
        mlocationClient!!.setLocationOption(mLocationOption)
        //使用缓存定位
        mLocationOption!!.setLocationCacheEnable(true)
        //启动定位
        mlocationClient!!.startLocation()
    }


    companion object {

        @JvmStatic
        fun newInstance() =
                UserLocationFragment()
    }


    override fun onResume() {
        super.onResume()
        user_map.onResume()
    }

    override fun onStop() {
        super.onStop()
        Log.d("==========", "onstop")
        //user_map.onDestroy()
        mlocationClient!!.stopLocation()
    }

    override fun onPause() {
        super.onPause()
        user_map.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        user_map.onSaveInstanceState(outState)
    }
}
