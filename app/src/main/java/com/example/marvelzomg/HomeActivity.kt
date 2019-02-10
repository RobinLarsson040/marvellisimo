package com.example.marvelzomg

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class HomeActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    private var usernameTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        usernameTextView = findViewById(R.id.usernameTextView)

        mAuth = FirebaseAuth.getInstance();

    }

    public override fun onStart() {
        super.onStart()
        val currentUser = mAuth!!.getCurrentUser()
        if (currentUser != null) {
            usernameTextView!!.text = currentUser.email
        } else {
            usernameTextView!!.text = "NOT LOGGED IN :/"
        }
    }

    fun onlineUsers(view: View) {
        startActivity(Intent(this, UsersActivity::class.java))
    }

    fun logout(view: View) {
        mAuth!!.signOut()
        startActivity(Intent(this, LoginActivity::class.java))
    }


}
