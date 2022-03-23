package com.developeruz.dagger2starter.network.models

data class SuccessResponse (
    val error:String?,
    val phoneNumber:String?,
    val success : Boolean
    )