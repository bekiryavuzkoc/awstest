package com.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amplifyframework.core.Amplify
import com.amplifyframework.core.model.query.Where
import com.amplifyframework.datastore.generated.model.TestName
import com.extensions.asLiveData
import com.extensions.emit
import com.extensions.mutableLiveEventOf
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class OperationsViewModel @Inject constructor() : ViewModel() {

    private val _toastText = mutableLiveEventOf<String>()
    val toastText = _toastText.asLiveData()

    fun create(name: String) {
        if (name.isNotEmpty()) {
            val item: TestName = TestName.builder()
                .name(name)
                .build()
            Amplify.DataStore.save(
                item,
                { showToastAndLog(item) },
                { error -> Log.e("Amplify", "Could not save item to DataStore", error) }
            )
        } else {
            _toastText.emit(INPUT_VALUE_TEXT)
        }
    }

    private fun showToastAndLog(item: TestName) {
        _toastText.emit(item.name)
        Log.i(TAG, item.id)
    }

    suspend fun readSpecificID(id: String): TestName = suspendCoroutine { cont ->
        if (id.isNotEmpty()) {
            Amplify.DataStore.query(
                TestName::class.java,
                Where.id(id),
                { items ->
                    if (!items.hasNext()) {
                        _toastText.emit(NO_USER_IN_DATABASE)
                    } else {
                        while (items.hasNext()) {
                            val item = items.next()
                            Log.i("Amplify", "Person name: " + item.name)
                            cont.resume(item)
                        }
                    }
                },
                { failure -> Log.e("Tutorial", "Could not query DataStore", failure) }
            )
        } else {
            _toastText.emit(INPUT_VALUE_TEXT)
        }
    }

    fun updateSpecificID(id: String, age: String) {
        if (id.isNotEmpty()) {
            viewModelScope.launch {
                val item = readSpecificID(id)

                val updatedModel = item.copyOfBuilder().age(age.toInt()).build()

                Amplify.DataStore.save(
                    item,
                    { showToastAndLog(updatedModel) },
                    { error -> Log.e("Amplify", "Could not updated item", error) }
                )
            }
        } else {
            _toastText.emit(INPUT_VALUE_TEXT)
        }
    }

    fun deleteUser(id: String) {
        if (id.isNotEmpty()) {
            val item = TestName.justId(id)

            Amplify.DataStore.delete(
                item,
                { _toastText.emit(item.name + " is deleted") },
                { error -> Log.e("Amplify", "Could not deleted item", error) }
            )
        } else {
            _toastText.emit(INPUT_VALUE_TEXT)
        }
    }

    fun readAll() {
        Amplify.DataStore.query(
            TestName::class.java,
            { items ->
                if (!items.hasNext()) {
                    _toastText.emit(NO_USERS_IN_DATABASE)
                } else {
                    while (items.hasNext()) {
                        val item = items.next()
                        Log.i("Amplify", "Queried item: " + item.id + " " + item.name)
                    }
                }
            },
            { failure -> Log.e("Tutorial", "Could not query DataStore", failure) }
        )
    }

    private companion object {
        const val INPUT_VALUE_TEXT = "Please input the value in the correct form"
        const val NO_USERS_IN_DATABASE = "There are not any users in database"
        const val NO_USER_IN_DATABASE = "There are not a user in database with that ID"
        const val TAG = "OperationsViewModel"
    }
}