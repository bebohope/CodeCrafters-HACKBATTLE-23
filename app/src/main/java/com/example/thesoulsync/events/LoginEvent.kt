package com.example.thesoulsync.events

sealed interface LoginEvent{
    data class Login(val email : String , val password : String) : LoginEvent
    data object LoginFailed : LoginEvent

}