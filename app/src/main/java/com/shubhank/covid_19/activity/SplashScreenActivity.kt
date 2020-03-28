package com.shubhank.covid_19.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.shubhank.covid_19.R

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({

            val mainActivity = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(mainActivity)
            finish()

        }, 1000)

    }
}
