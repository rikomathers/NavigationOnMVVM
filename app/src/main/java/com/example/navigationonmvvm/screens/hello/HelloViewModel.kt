package com.example.navigationonmvvm.screens.hello

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.navigationonmvvm.R
import com.example.navigationonmvvm.navigator.Navigator
import com.example.navigationonmvvm.screens.base.BaseViewModel
import com.example.navigationonmvvm.screens.edit.EditFragment

class HelloViewModel(
    private val navigator: Navigator,
    screen: HelloFragment.Screen
) : BaseViewModel() {

    private val _currentMessageLiveData = MutableLiveData<String>()
    val currentMessageLiveData: LiveData<String> = _currentMessageLiveData

    init {
        _currentMessageLiveData.value = navigator.getString(R.string.app_name)
    }

    fun onEditPressed() {
        navigator.launch(EditFragment.Screen(currentMessageLiveData.value!!))
    }

    override fun onResult(result: Any) {
        if (result is String) {
            _currentMessageLiveData.value = result
        }
    }
}