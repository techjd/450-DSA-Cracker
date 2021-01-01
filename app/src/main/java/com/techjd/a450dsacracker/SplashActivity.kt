package com.techjd.a450dsacracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.techjd.a450dsacracker.ui.activities.MainActivity

class SplashActivity : AppCompatActivity() {
    private var SPLASH_TIME_OUT: Long = 1000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val navigate = Intent(this, MainActivity::class.java)
            startActivity(navigate)
            finish()
        }, SPLASH_TIME_OUT)
    }
}