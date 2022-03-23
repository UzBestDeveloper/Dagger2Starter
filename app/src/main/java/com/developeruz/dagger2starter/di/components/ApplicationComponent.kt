package com.developeruz.dagger2starter.di.components

import com.developeruz.dagger2starter.di.components.subcomponents.ActivityComponent
import com.developeruz.dagger2starter.di.components.subcomponents.FragmentComponent
import com.developeruz.dagger2starter.di.modules.NetworkModule
import com.developeruz.dagger2starter.di.modules.SubcomponentsModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, SubcomponentsModule::class])
interface ApplicationComponent {

    fun activityComponent(): ActivityComponent.Factory

    fun fragmentComponent(): FragmentComponent.Factory

}