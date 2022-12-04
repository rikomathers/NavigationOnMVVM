package com.example.navigationonmvvm.screens.edit

import com.example.navigationonmvvm.screens.base.BaseViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.navigationonmvvm.Event
import com.example.navigationonmvvm.navigator.Navigator

class EditViewModel(
    private val navigator: Navigator,
    screen: EditFragment.Screen
) : BaseViewModel() {

    private val _initialMessageEvent = MutableLiveData<Event<String>>()
    val initialMessageEvent: LiveData<Event<String>> = _initialMessageEvent

    init {
        _initialMessageEvent.value = Event(screen.initialValue)
    }

    fun onSavePressed(message: String) {
        navigator.goBack(message)
    }

    fun onCancelPressed() {
        navigator.goBack()
    }
}