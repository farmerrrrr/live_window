package com.example.finedust

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.finedust.databinding.JoinSettingBinding

class Join:AppCompatActivity() {
    private lateinit var joinbinding : JoinSettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        joinbinding = JoinSettingBinding.inflate(layoutInflater)
        setContentView(joinbinding.root)

        joinbinding.joinBtn.setOnClickListener{
            val login_intent = Intent(this, Login::class.java)
            startActivity(login_intent)
            Toast.makeText(this,"회원가입 완료!", Toast.LENGTH_SHORT).show()
        }
    }
}