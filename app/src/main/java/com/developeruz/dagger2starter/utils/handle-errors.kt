package com.developeruz.dagger2starter.utils

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.annotations.SerializedName
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

fun Throwable.handle(): ErrorData? {
    var errorData: ErrorData? = null
    if (this is HttpException) {
        val body = this.response()?.errorBody()
        val gson = Gson()
        val adapter: TypeAdapter<ErrorData> = gson.getAdapter(ErrorData::class.java)
        try {
            errorData = adapter.fromJson(body?.string())
        } catch (e: IOException) {
            try {
                errorData = this.message?.let { ErrorData("", it, "") }
            } catch (e: Exception) {
                println("error ${this.javaClass.name} -> ${e.message ?: e.localizedMessage}")
            }
            println("error ${this.javaClass.name} -> ${e.message ?: e.localizedMessage}")
        }
    }
    return errorData
}

fun ResponseBody.handle(): ErrorData? {
    var errorData: ErrorData? = null
    val body = this
    val gson = Gson()
    val adapter: TypeAdapter<ErrorData> = gson.getAdapter(ErrorData::class.java)
    try {
        errorData = adapter.fromJson(body.string())
    } catch (e: IOException) {
        println("error ${this.javaClass.name} -> ${e.message ?: e.localizedMessage}")
    }
    return errorData
}

data class ErrorData(
    @SerializedName("code")
    val code: String,
    @SerializedName("message")
    var message: String,
    @SerializedName("data")
    val data: Any
)