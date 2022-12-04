package com.example.navigationonmvvm.navigator

import com.example.navigationonmvvm.screens.base.BaseScreen

interface Navigator {

    fun launch(screen: BaseScreen) //запустить экран

    fun goBack(result: Any? = null) //выйти на предыдущий экран с возможностью передать ему какой то результат

    fun showToast(messageRes: Int) // показать тост соообщения

    fun getString(messageRes: Int): String  //получить из вьюмодели стрингу по идентификатору
}