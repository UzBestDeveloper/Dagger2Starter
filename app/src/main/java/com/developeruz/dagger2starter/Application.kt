package com.developeruz.dagger2starter

import android.app.Application
import com.developeruz.dagger2starter.di.components.ApplicationComponent
import com.developeruz.dagger2starter.di.components.DaggerApplicationComponent

class Application: Application() {

    val appComponent: ApplicationComponent = DaggerApplicationComponent.create()
}