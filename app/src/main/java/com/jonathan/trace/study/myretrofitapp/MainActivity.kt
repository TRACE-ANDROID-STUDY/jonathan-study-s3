package com.jonathan.trace.study.myretrofitapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jonathan.trace.study.myretrofitapp.data.LoginRequest
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val responseBody = intent.getStringExtra("response")
        mainText.text = responseBody
    }
}
