package com.developeruz.dagger2starter.network.api

import com.developeruz.dagger2starter.network.models.SuccessResponse
import com.developeruz.dagger2starter.network.models.auth.signIn.SignInRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    @POST("Account/IsUserRegistered")
    suspend fun isUserRegistered(
        @Body request: SignInRequest
    ): SuccessResponse

}