package com.example.navigationonmvvm.navigator

import android.app.Application
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.navigationonmvvm.Event
import com.example.navigationonmvvm.MainActivity
import com.example.navigationonmvvm.R
import com.example.navigationonmvvm.screens.base.BaseScreen

const val ARG_SCREEN = "ARG_SCREEN"

class MainNavigator(
    application: Application
) : AndroidViewModel(application), Navigator {

    val mainActivityActions = MainActivityActions()
    private val _result = MutableLiveData<Event<Any>>()
    val result: LiveData<Event<Any>> = _result

    override fun launch(screen: BaseScreen) {
        mainActivityActions.addAction {
            launchFragment(it, screen)
        }
    }

    override fun goBack(result: Any?) {
        if (result != null) {
            _result.value = Event(result)
        }
        mainActivityActions.addAction {
            it.onBackPressed()
        }
    }

    override fun onCleared() {
        super.onCleared()
        mainActivityActions.clear()
    }

    override fun showToast(messageRes: Int) {
        Toast.makeText(getApplication(), messageRes, Toast.LENGTH_SHORT).show()
    }

    override fun getString(messageRes: Int): String {
        return getApplication<Application>().getString(messageRes)
    }

    fun launchFragment(activity: MainActivity, screen: BaseScreen, addToBackStack: Boolean = true) {
        val fragment = screen.javaClass.enclosingClass.newInstance() as Fragment
        fragment.arguments = bundleOf(ARG_SCREEN to screen)
        val transaction = activity.supportFragmentManager.beginTransaction()
        if (addToBackStack) transaction.addToBackStack(null)
        transaction.replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}