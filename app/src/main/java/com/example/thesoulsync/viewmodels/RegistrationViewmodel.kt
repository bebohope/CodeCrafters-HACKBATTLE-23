package com.example.thesoulsync.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thesoulsync.events.RegistrationEvents
import com.example.thesoulsync.states.LoginStates
import com.example.thesoulsync.states.RegistrationStates
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

private val TAG = "RegistrationViewmodel"
class RegistrationViewmodel(private val auth: FirebaseAuth) : ViewModel() {
    private val _state = MutableStateFlow(RegistrationStates())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        RegistrationStates()
    )

    fun onEvent(event: RegistrationEvents){
        when(event){
            is RegistrationEvents.register -> {
                _state.value.email?.let {
                    _state.value.password?.let { it1 ->
                        auth.createUserWithEmailAndPassword(it, it1)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success")
                                    val user = auth.currentUser

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.exception)

                                }
                            }
                    }
                }
            }
        }
    }
}