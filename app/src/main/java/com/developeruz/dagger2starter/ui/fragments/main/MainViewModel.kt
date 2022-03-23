package com.developeruz.dagger2starter.ui.fragments.main

import com.developeruz.dagger2starter.di.scopes.FragmentScope
import com.developeruz.dagger2starter.ui.base.BaseViewModel
import com.developeruz.dagger2starter.network.repo.AuthRepository
import javax.inject.Inject

@FragmentScope
class MainViewModel @Inject constructor(private val authRepository: AuthRepository): BaseViewModel() {


}