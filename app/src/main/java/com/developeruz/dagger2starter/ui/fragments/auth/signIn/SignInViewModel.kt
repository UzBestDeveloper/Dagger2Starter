package com.developeruz.dagger2starter.ui.fragments.auth.signIn

import androidx.lifecycle.LiveData
import com.developeruz.dagger2starter.di.scopes.FragmentScope
import com.developeruz.dagger2starter.network.models.SuccessResponse
import com.developeruz.dagger2starter.ui.base.BaseViewModel
import com.developeruz.dagger2starter.ui.fragments.auth.AuthRepository
import com.developeruz.dagger2starter.utils.lifecycle.SingleEvent
import javax.inject.Inject

@FragmentScope
class SignInViewModel @Inject constructor(private val authRepository: AuthRepository): BaseViewModel() {

    private val _successResponse = SingleEvent<SuccessResponse>()
    val successResponse: LiveData<SuccessResponse> get() = _successResponse

    fun isUserRegistered(phone: String) = authRepository.isUserRegistered(phone).proceed {
        _successResponse.value = it
    }

}