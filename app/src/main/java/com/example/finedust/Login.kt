package com.example.finedust

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.finedust.databinding.LoginSettingBinding

class Login:AppCompatActivity() {

    private lateinit var binding: LoginSettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginSettingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //로그인 버튼 클릭했을 때
        binding.loginBtn.setOnClickListener{

        }

        //회원가입 버튼 클릭했을 때
        binding.joinEnterBtn.setOnClickListener{
         val intent = Intent(this,Join::class.java)
         startActivity(intent)
        }



    }
}