package com.example.thesoulsync.events

sealed interface LoginEvent{
    data object Login: LoginEvent
    data object LoginFailed : LoginEvent
    data class setEmail(val email : String ) : LoginEvent
    data class setPassword(val password : String) : LoginEvent

}