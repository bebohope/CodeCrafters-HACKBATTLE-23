package com.example.thesoulsync.events

sealed interface RegistrationEvents{
    data class register(val email : String , val password : String) : RegistrationEvents
}