package com.nordokod.nextlaunches.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import com.nordokod.nextlaunches.R
import com.nordokod.nextlaunches.ui.MainActivity
import kotlinx.android.synthetic.main.activity_splash_screen.*
import kotlinx.coroutines.*

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.SplashTheme)
        setContentView(R.layout.activity_splash_screen)
        val animation = AnimationUtils.loadAnimation(applicationContext,R.anim.rocket_splash)
        main_icon.startAnimation(animation)
        GlobalScope.launch {
            delay(1500)
            val intent = Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}