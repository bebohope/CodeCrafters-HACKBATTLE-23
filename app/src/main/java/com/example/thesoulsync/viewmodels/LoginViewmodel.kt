package com.example.thesoulsync.viewmodels

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thesoulsync.events.LoginEvent
import com.example.thesoulsync.states.LoginStates
import com.example.thesoulsync.user.User
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

private val TAG = "LoginViewmodel"
class LoginViewmodel(private val auth: FirebaseAuth) : ViewModel() {

    private val _states  = MutableStateFlow(LoginStates())
    val states = _states.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        LoginStates())

    fun onEvent(event : LoginEvent){
        when(event){
            is LoginEvent.Login -> {
                auth.signInWithEmailAndPassword(event.email, event.password)
                    .addOnCompleteListener() { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success")
                            viewModelScope.launch {
                                _states.emit(
                                    _states.value.copy(
                                        user = User(auth.currentUser?.uid , ""),
                                        logged = true
                                    )
                                )
                                Log.d(TAG, "signInWithEmail:success" + "${_states.value.user?.uuid}")
                            }



                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.exception)
                            onEvent(LoginEvent.LoginFailed)

                        }
                    }
            }
            LoginEvent.LoginFailed -> {
                throw LoginFailedException("")
            }
        }
    }

}

class LoginFailedException(message : String) : Exception(message) {}