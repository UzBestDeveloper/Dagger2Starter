package com.developeruz.dagger2starter.network.models.auth.signIn

data class SignInRequest(
    val password: String? = null,
    val passwordConfirm: String? = null,
    val phoneNumber: String? = null,
    val smsCode: String? = null,
    val username: String? = null,
    val appKeyHash: String? = null
)