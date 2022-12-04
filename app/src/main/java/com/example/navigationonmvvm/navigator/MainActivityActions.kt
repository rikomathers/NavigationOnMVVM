package com.example.navigationonmvvm.navigator

import com.example.navigationonmvvm.MainActivity

typealias MainActivityAction = (MainActivity) -> Unit

class MainActivityActions {

    var mainActivity: MainActivity? = null
        set(activity) {
            field = activity
            if (activity != null) {
                actions.forEach {
                    it.invoke(activity)
                }
                actions.clear()
            }
        }
    private val actions = mutableListOf<MainActivityAction>()

    fun addAction(action: MainActivityAction) {
        val activity = this.mainActivity
        if (activity != null) {
            action.invoke(activity)
        } else {
            actions.add(action)
        }
    }

    fun clear() {
        actions.clear()
    }
}