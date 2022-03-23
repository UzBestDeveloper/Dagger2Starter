package com.developeruz.dagger2starter.network.repo

import com.developeruz.dagger2starter.network.api.AuthService
import com.developeruz.dagger2starter.network.models.auth.signIn.SignInRequest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val authService: AuthService
) {

    fun isUserRegistered(phone: String) = flow {
        val request = SignInRequest("","",phone,"","","")
        val response = authService.isUserRegistered(request)
        emit(response)
    }

}