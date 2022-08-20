package com.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.generated.model.TestName
import com.extensions.asLiveData
import com.extensions.emit
import com.extensions.mutableLiveEventOf
import javax.inject.Inject

class OperationsViewModel @Inject constructor() : ViewModel() {

    private val _toastText = mutableLiveEventOf<String>()
    val toastText = _toastText.asLiveData()

    fun create(name: String) {
        if (name.isNotEmpty()) {
            val item: TestName = TestName.builder()
                .name("First Test Name")
                .build()
            Amplify.DataStore.save(
                item,
                { _toastText.emit(item.name) },
                { error -> Log.e("Amplify", "Could not save item to DataStore", error) }
            )
        } else {
            _toastText.emit(INPUT_VALUE_TEXT)
        }
    }

    fun readAll() {

    }

    fun readSpecificID() {

    }

    fun updateSpecificID() {

    }

    fun delete() {

    }

    fun onClickSaveButton() {

    }

    private companion object {
        const val INPUT_VALUE_TEXT = "Please input the value in the correct form"
    }
}