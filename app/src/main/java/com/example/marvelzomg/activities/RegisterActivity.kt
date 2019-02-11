package com.example.marvelzomg.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText


import android.widget.Toast
import android.content.Intent
import android.view.View
import com.example.marvelzomg.R
import com.example.marvelzomg.services.FireBaseService


class RegisterActivity : AppCompatActivity() {

    var emailEditText: EditText? = null
    var passwordEditText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_activity)

        emailEditText = findViewById(R.id.input_email)
        passwordEditText = findViewById(R.id.input_password)
    }

    fun register(view: View) {
        if (!emailEditText!!.text.isEmpty() && !passwordEditText!!.text.isEmpty()) {
            FireBaseService.createUser(
                emailEditText!!.text.toString(), passwordEditText!!.text.toString(), this
            )
        } else {
            Toast.makeText(this, "Please fill up the Credentials :|", Toast.LENGTH_SHORT).show()
        }
    }

    fun linkLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

}
