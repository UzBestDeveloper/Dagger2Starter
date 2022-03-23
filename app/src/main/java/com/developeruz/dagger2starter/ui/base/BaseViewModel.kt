package com.developeruz.dagger2starter.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.developeruz.dagger2starter.utils.ErrorData
import com.developeruz.dagger2starter.utils.exception
import com.developeruz.dagger2starter.utils.handle
import com.developeruz.dagger2starter.utils.lifecycle.SingleEvent
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*

abstract class BaseViewModel : ViewModel() {

    private val _loader = MutableLiveData<Boolean>()
    val loader: LiveData<Boolean> get() = _loader

    val _errorData = SingleEvent<ErrorData>()
    val errorData: LiveData<ErrorData> get() = _errorData

    fun <T> Flow<T>.proceed(action: suspend (T) -> Unit = { }): Job {
        return onStart { start() }
            .catch {
                it.exception(it.stackTraceToString())
                loader(false)
                catch(it)
            }
            .onEach {
                loader(false)
                action.invoke(it)
            }
            .launchIn(viewModelScope)
    }


    open fun start() {
        loader(true)
    }

    private fun catch(cause: Throwable) {
        val errorData = cause.handle()
        if (errorData != null) {
                _errorData.value = errorData!!
        } else println("error ${this.javaClass.name} -> null")
    }

    private fun loader(isLoading: Boolean) {
        _loader.postValue(isLoading)
    }
}