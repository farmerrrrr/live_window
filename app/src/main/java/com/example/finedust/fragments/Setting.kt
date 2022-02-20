package com.example.finedust.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.finedust.Ask
import com.example.finedust.Login
import com.example.finedust.R

class Setting : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_setting, container, false)

        //로그인 버튼
        var login_btn: Button = view.findViewById(R.id.setting_login)

        login_btn.setOnClickListener {

            val login_intent = Intent(getActivity(), Login::class.java)
            startActivity(login_intent)
        }

        //문의하기 버튼
        var ask_btn: Button = view.findViewById(R.id.setting_question)

        ask_btn.setOnClickListener{

            val ask_intent = Intent(getActivity(), Ask::class.java)
            startActivity(ask_intent)
        }


        return view

    }

}