package com.example.navigationonmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.example.navigationonmvvm.navigator.MainNavigator
import com.example.navigationonmvvm.screens.base.BaseFragment
import com.example.navigationonmvvm.screens.hello.HelloFragment

class MainActivity : AppCompatActivity() {

    private val navigator: MainNavigator by viewModels {
        ViewModelProvider.AndroidViewModelFactory(
            application
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            navigator.launchFragment(this, HelloFragment.Screen(), false)
        }
        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentCallbacks, false)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onResume() {
        super.onResume()
        navigator.mainActivityActions.mainActivity = this
    }

    override fun onPause() {
        navigator.mainActivityActions.mainActivity = null
        super.onPause()
    }

    private val fragmentCallbacks = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewCreated(
            fm: FragmentManager,
            f: Fragment,
            v: View,
            savedInstanceState: Bundle?
        ) {
            if (supportFragmentManager.backStackEntryCount > 0) {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            } else {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            }
            val result = navigator.result.value?.getValue() ?: return
            if (f is BaseFragment) {
                f.viewModel.onResult(result)
            }
        }
    }
}