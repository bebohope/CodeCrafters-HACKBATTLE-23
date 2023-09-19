package com.example.thesoulsync

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.example.thesoulsync.databinding.ActivityMainBinding
import com.example.thesoulsync.events.LoginEvent
import com.example.thesoulsync.viewmodels.LoginViewmodel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var auth : FirebaseAuth
    lateinit var loginViewmodel: LoginViewmodel
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        auth = Firebase.auth
        loginViewmodel = LoginViewmodel(auth)

        binding.userName.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {}
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    // Call suspend function in coroutine scope
                    lifecycleScope.launch {
                        loginViewmodel.onEvent(LoginEvent.setEmail((s.toString())))
                    }
                }
            }
        )

        binding.userName2.addTextChangedListener(
            object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {}
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    // Call suspend function in coroutine scope
                    lifecycleScope.launch {
                        loginViewmodel.onEvent(LoginEvent.setPassword((s.toString())))
                    }
                }
            }
        )


    }
}