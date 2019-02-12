package com.example.marvelzomg.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.marvelzomg.R
import com.example.marvelzomg.services.FireBaseService
import com.google.firebase.auth.FirebaseAuth

class UsersActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        FireBaseService.getOnlineUsers()
    }
}
