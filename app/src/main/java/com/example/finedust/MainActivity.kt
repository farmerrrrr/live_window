package com.example.finedust

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.finedust.databinding.ActivityMainBinding
import com.example.finedust.fragments.Home
import com.example.finedust.fragments.Predict
import com.example.finedust.fragments.Setting
import com.example.finedust.fragments.VentSystem
import com.google.android.gms.location.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlin.math.roundToInt


class MainActivity : AppCompatActivity() {

    //바텀 네비게이션 부착
    private val fragmentHome = Home()
    private val fragmentPredict = Predict()
    //private val fragmentVentSystem = VentSystem()
    private val fragmentSetting = Setting()
    private val btbar: BottomNavigationView by lazy { findViewById(R.id.menu_bar) }
    private val fl: FrameLayout by lazy { findViewById(R.id.my_nav_host) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btbar.setOnNavigationItemSelectedListener {
            replaceFragment(
                when (it.itemId) {
                    R.id.menu_home -> fragmentHome
                    R.id.menu_forecast -> fragmentPredict
                    else -> fragmentSetting
                })
            true
        }


        //lateinit : 나중에 설정한다
        //private lateinit var mBinding: ActivityMainBinding
        /*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        //네비게이션들을 담는 호스트
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.my_nav_host) as NavHostFragment

        //네비게이션 컨트롤러
        val navController = navHostFragment.navController

        //바텀 네비게이션 뷰와 네비게이션을 묶어준다
        NavigationUI.setupWithNavController(mBinding.menuBar, navController)

    } */
    }
    private fun replaceFragment(fragment: Fragment)
    { supportFragmentManager.beginTransaction().replace(fl.id, fragment).commit() }





}

