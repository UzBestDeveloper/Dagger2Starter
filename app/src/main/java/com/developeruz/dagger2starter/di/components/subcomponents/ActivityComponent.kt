package com.developeruz.dagger2starter.di.components.subcomponents

import com.developeruz.dagger2starter.di.scopes.ActivityScope
import com.developeruz.dagger2starter.ui.activities.MainActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface ActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ActivityComponent
    }

    fun inject(activity: MainActivity)

}