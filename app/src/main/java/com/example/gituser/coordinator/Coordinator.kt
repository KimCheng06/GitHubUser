package com.example.gituser.coordinator

class Coordinator(private val navigator: Navigator) {

    fun toUserInfoPage(userName: String) = navigator.toUserInfoPage(userName)
}