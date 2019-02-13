package com.example.marvelzomg.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.example.marvelzomg.R
import com.example.marvelzomg.services.FireBaseService
import com.google.firebase.auth.FirebaseAuth


class HomeActivity : AppCompatActivity() {

    private var usernameTextView: TextView? = null
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        usernameTextView = findViewById(R.id.usernameTextView)
    }

    public override fun onStart() {
        super.onStart()
        FireBaseService.updateUserFavoriteCharacters()
        if (auth.currentUser != null) {
            usernameTextView!!.text = auth.currentUser!!.email
        } else {
            usernameTextView!!.text = "NOT LOGGED IN :/"
        }
    }

    fun onlineUsers(view: View) {
        startActivity(Intent(this, UsersActivity::class.java))
    }

    fun logout(view: View) {
        FireBaseService.signOut()
        startActivity(Intent(this, LoginActivity::class.java))
    }

    fun jonasTest(view: View){
        startActivity(Intent(this, CharacterListActivity::class.java))
    }


}
