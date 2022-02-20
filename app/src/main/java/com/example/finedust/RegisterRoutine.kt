package com.example.finedust

import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.finedust.fragments.VentSystem
import java.util.*

/*

class RegisterRoutine:AppCompatActivity() {

    //private lateinit var binding:RegisterRoutineBinding
    var timeString = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //뷰 바인딩을 사용함으로써 findVeiwById를 사용하지 않고 참조
        //inflate 메서드 호출 -> 뷰가 결합된 클래스 객체 생성
        //binding = RegisterRoutineBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.enterTime.setOnClickListener{
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                timeString = "${hourOfDay}시 ${minute}분"
                binding.enterTime.text = timeString+"부터" +"\n" + " 시작 됩니다"
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE),true).show()
        }

        binding.saveBtn.setOnClickListener{
            Toast.makeText(this,"저장 완료!",Toast.LENGTH_SHORT).show()
        }





    }
}
*/

