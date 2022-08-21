package com.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.viewmodel.event.Event


fun <T> mutableLiveEventOf(): MutableLiveData<Event<T>> {
    return mutableLiveDataOf()
}


fun <T> mutableLiveDataOf(initialValue: T? = null): MutableLiveData<T> {
    return when (initialValue) {
        null -> MutableLiveData()
        else -> MutableLiveData(initialValue)
    }
}

fun <T> MutableLiveData<T>.asLiveData(): LiveData<T> {
    return this
}

fun <T> MutableLiveData<Event<T>>.emit(event: T) {
    value = Event(event)
}