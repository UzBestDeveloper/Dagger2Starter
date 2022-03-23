package com.developeruz.dagger2starter.di.modules

import com.developeruz.dagger2starter.di.components.subcomponents.ActivityComponent
import com.developeruz.dagger2starter.di.components.subcomponents.FragmentComponent
import dagger.Module

@Module(subcomponents = [ActivityComponent::class,FragmentComponent::class])
class SubcomponentsModule