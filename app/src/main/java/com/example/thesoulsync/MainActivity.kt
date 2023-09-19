package com.example.thesoulsync

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.thesoulsync.databinding.ActivityMainBinding
import com.example.thesoulsync.viewmodels.LoginViewmodel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

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

        //binding.userName.addTextChangedListener();


    }
}