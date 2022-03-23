package com.developeruz.dagger2starter.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.developeruz.dagger2starter.Application
import com.developeruz.dagger2starter.R
import com.developeruz.dagger2starter.di.components.subcomponents.ActivityComponent

class MainActivity : AppCompatActivity() {

    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent = (applicationContext as Application).appComponent.activityComponent().create()
        activityComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}