package com.example.thesoulsync.states

import com.example.thesoulsync.user.User

data class LoginStates(
    var email : String? = "" ,
    var password : String? = "",
    var logged : Boolean = false,
    var user : User? = null
)