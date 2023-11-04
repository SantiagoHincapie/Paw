package com.example.paws

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.paws.LogIn.LogIn

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen=installSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        splashScreen.setKeepOnScreenCondition { true }

        Thread.sleep(1500)
        val intent=Intent(this,LogIn::class.java)
        startActivity(intent)
        finish()
    }
}