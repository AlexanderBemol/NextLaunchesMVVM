package com.nordokod.nextlaunches.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nordokod.nextlaunches.R
import com.nordokod.nextlaunches.ui.launches.LaunchesFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LaunchesFragment.newInstance())
                .commitNow()
        }
    }
}