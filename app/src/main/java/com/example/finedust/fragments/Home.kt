package com.example.finedust.fragments

import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.finedust.R
import com.google.android.gms.location.*
import kotlin.math.roundToInt

class Home : Fragment() {
    val TAG: String = "로그"
    lateinit var btnStartupdate: Button
    lateinit var locate: TextView
    private var mFusedLocationProviderClient: FusedLocationProviderClient? = null // 현재 위치를 가져오기 위한 변수
    lateinit var mLastLocation: Location // 위치 값을 가지고 있는 객체
    internal lateinit var mLocationRequest: LocationRequest // 위치 정보 요청의 매개변수를 저장하는
    private val REQUEST_PERMISSION_LOCATION = 10



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // 화면뷰 inflate.
        /*btnStartupdate = view.findViewById(R.id.lo_button)*/

        mLocationRequest = LocationRequest.create().apply {
            interval = 20000 // 업데이트 간격 단위(밀리초)
            fastestInterval = 10000 // 가장 빠른 업데이트 간격 단위(밀리초)
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY // 정확성
            maxWaitTime = 20000 // 위치 갱신 요청 최대 대기 시간 (밀리초)
        }
        btnStartupdate.setOnClickListener {
            if (checkPermissionForLocation()) {
                startLocationUpdates()
            }
        }
        return view
    }


        protected fun startLocationUpdates() {
            Log.d(TAG, "startLocationUpdates()")

            //FusedLocationProviderClient의 인스턴스를 생성.
            mFusedLocationProviderClient =
                LocationServices.getFusedLocationProviderClient(this.activity)
            if (ActivityCompat.checkSelfPermission
                    (this.requireActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this.requireActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION
                )
                != PackageManager.PERMISSION_GRANTED
            ) {
                Log.d(TAG, "startLocationUpdates() 두 위치 권한중 하나라도 없는 경우 ")
                return
            }
            Log.d(TAG, "startLocationUpdates() 위치 권한이 하나라도 존재하는 경우")
            // 기기의 위치에 관한 정기 업데이트를 요청하는 메서드 실행
            // 지정한 루퍼 스레드(Looper.myLooper())에서 콜백(mLocationCallback)으로 위치 업데이트를 요청합니다.
            mFusedLocationProviderClient!!.requestLocationUpdates(
                mLocationRequest,
                mLocationCallback,
                Looper.myLooper()
            )


        }

        // 시스템으로 부터 위치 정보를 콜백으로 받음
        private val mLocationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                Log.d(TAG, "onLocationResult()")
                // 시스템에서 받은 location 정보를 onLocationChanged()에 전달
                locationResult.lastLocation
                onLocationChanged(locationResult.lastLocation)
            }
        }

        // 시스템으로 부터 받은 위치정보를 화면에 갱신해주는 메소드
        fun onLocationChanged(location: Location) {
            Log.d(TAG, "onLocationChanged()")
            mLastLocation = location
            locate.text = (((mLastLocation.latitude * 100).roundToInt()) / 100f).toString() + "," + (((mLastLocation.longitude * 100).roundToInt()) / 100f).toString()
            //영어로 주소 나타내기
            //locate.text=getAddress(mLastLocation.latitude,mLastLocation.longitude)
        }

        // 위치 업데이터를 제거 하는 메서드
        private fun stoplocationUpdates() {
            Log.d(TAG, "stoplocationUpdates()")
            // 지정된 위치 결과 리스너에 대한 모든 위치 업데이트를 제거
            mFusedLocationProviderClient!!.removeLocationUpdates(mLocationCallback)
        }

        private fun getAddress(lat: Double, lng: Double): String {
            val geocoder = Geocoder(this.activity)
            val list = geocoder.getFromLocation(lat, lng, 1)
            return list[0].getAddressLine(0)
        }

        // 위치 권한이 있는지 확인하는 메서드
        fun checkPermissionForLocation(): Boolean {
            Log.d(TAG, "checkPermissionForLocation()")
            // Android 6.0 Marshmallow 이상에서는 지리 확보(위치) 권한에 추가 런타임 권한이 필요합니다.
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (context?.checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "checkPermissionForLocation() 권한 상태 : O")
                    true
                } else {
                    // 권한이 없으므로 권한 요청 알림 보내기
                    Log.d(TAG, "checkPermissionForLocation() 권한 상태 : X")
                    ActivityCompat.requestPermissions(
                        this.requireActivity(),
                        arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                        REQUEST_PERMISSION_LOCATION
                    )
                    false
                }
            } else {
                true
            }
        }

        // 사용자에게 권한 요청 후 결과에 대한 처리 로직
        override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
        ) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            Log.d(TAG, "onRequestPermissionsResult()")
            if (requestCode == REQUEST_PERMISSION_LOCATION) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "onRequestPermissionsResult() _ 권한 허용 클릭")
                    startLocationUpdates()
                }
            }

        }




}



