package com.developeruz.dagger2starter.di.components.subcomponents

import com.developeruz.dagger2starter.di.scopes.FragmentScope
import com.developeruz.dagger2starter.ui.fragments.main.MainFragment
import com.developeruz.dagger2starter.ui.fragments.auth.signIn.SignInFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent
interface FragmentComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): FragmentComponent
    }

    fun inject(signInFragment: SignInFragment)

    fun inject(mainFragment: MainFragment)


}