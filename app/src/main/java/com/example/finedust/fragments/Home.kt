package com.example.finedust.fragments

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.os.StrictMode
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.example.finedust.R
import com.google.android.gms.location.*
import org.w3c.dom.*
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.net.URL
import java.util.*
import javax.xml.parsers.DocumentBuilderFactory
import kotlin.math.roundToInt

class Home : Fragment() {
    val TAG: String = "로그"

    lateinit var status1: TextView
    lateinit var locate: TextView
    private var mFusedLocationProviderClient: FusedLocationProviderClient? = null // 현재 위치를 가져오기 위한 변수
    lateinit var mLastLocation: Location // 위치 값을 가지고 있는 객체
    internal lateinit var mLocationRequest: LocationRequest // 위치 정보 요청의 매개변수를 저장하는
    private val REQUEST_PERMISSION_LOCATION = 10
    var inAddr = false

    var initem = false
    var addr: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // 화면뷰 inflate.
        /*btnStartupdate = view.findViewById(R.id.lo_button)*/
        StrictMode.enableDefaults();
        status1 =view.findViewById(R.id.outdoor)
        locate =view.findViewById(R.id.lo_name)



        mLocationRequest =  LocationRequest.create().apply {

            priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        }
        startLocationUpdates()







        return view
    }


    private fun startLocationUpdates() {

        //FusedLocationProviderClient의 인스턴스를 생성.
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this.requireActivity())
        if (ActivityCompat.checkSelfPermission(this.requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this.requireActivity(),Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        // 기기의 위치에 관한 정기 업데이트를 요청하는 메서드 실행
        // 지정한 루퍼 스레드(Looper.myLooper())에서 콜백(mLocationCallback)으로 위치 업데이트를 요청
        Looper.myLooper()?.let {
            mFusedLocationProviderClient!!.requestLocationUpdates(mLocationRequest, mLocationCallback,
                it
            )
        }
    }

    // 시스템으로 부터 위치 정보를 콜백으로 받음
    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            // 시스템에서 받은 location 정보를 onLocationChanged()에 전달
            locationResult.lastLocation
            onLocationChanged(locationResult.lastLocation)
        }
    }

    // 시스템으로 부터 받은 위치정보를 화면에 갱신해주는 메소드
    fun onLocationChanged(location: Location) {
        mLastLocation = location
        Log.d("testlo", "Lat: ${mLastLocation.latitude}, lon: ${mLastLocation.longitude}")
        val geocoder = Geocoder(this.activity, Locale.KOREAN)
        val list = geocoder.getFromLocation(mLastLocation.latitude, mLastLocation.longitude, 1)
        //return list[0].getAddressLine(0)

        val str =list[0].getAddressLine(0).substring(11)
        val arr =str.split(" ")
        Log.d("testlo", "주소: ${arr[0]}")
        locate.setText(arr[0])
        try {
            val url = URL(
                "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?stationName="+arr[0]+"&dataTerm=month&pageNo=1&numOfRows=1&returnType=xml&serviceKey=It3DI%2FI3lArun2UrANJKnAFoYH%2BuUb9iO2IjExqaGIs%2BtumRHW0pby4%2FR%2BDbzEjgga2wgqiq387kFR9GRyglUA%3D%3D"
            ) //검색 URL부분
            val parserCreator = XmlPullParserFactory.newInstance()
            val parser = parserCreator.newPullParser()
            parser.setInput(url.openStream(), null)
            var parserEvent = parser.eventType
            println("파싱시작합니다.")
            while (parserEvent != XmlPullParser.END_DOCUMENT) {
                when (parserEvent) {
                    XmlPullParser.START_TAG -> {
                        if ((parser.name == "pm10Value")) { //title 만나면 내용을 받을수 있게 하자
                            inAddr = true
                        }
                        if ((parser.name == "message")) { //message 태그를 만나면 에러 출력
                            status1.text = status1.text.toString() + "에러"
                            //여기에 에러코드에 따라 다른 메세지를 출력하도록 할 수 있다.
                        }
                    }
                    XmlPullParser.TEXT -> {
                        if (inAddr) { //isTitle이 true일 때 태그의 내용을 저장.
                            addr = parser.text
                            inAddr = false
                        }

                    }
                    XmlPullParser.END_TAG -> if ((parser.name == "item")) {
                        status1.text =
                            (addr)
                        initem = false
                    }
                }
                parserEvent = parser.next()
            }
        } catch (e: Exception) {
            status1.text = "에러가..났습니다..."
        }
    }


    // 위치 권한이 있는지 확인하는 메서드
    private fun checkPermissionForLocation(context: Context): Boolean {
        // Android 6.0 Marshmallow 이상에서는 위치 권한에 추가 런타임 권한이 필요
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                true
            } else {
                // 권한이 없으므로 권한 요청 알림 보내기
                ActivityCompat.requestPermissions(this.requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_PERMISSION_LOCATION)
                false
            }
        } else {
            true
        }
    }

    // 사용자에게 권한 요청 후 결과에 대한 처리 로직
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSION_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startLocationUpdates()
            }
        }
    }
}
