package com.example.navigationonmvvm

typealias Users = List<User>
typealias Result<T> = (T) -> Unit

class Test {
    fun withTypealias(someLyambda: Result<String>) {
        val a:Result<String> = {

        }
    }
}

