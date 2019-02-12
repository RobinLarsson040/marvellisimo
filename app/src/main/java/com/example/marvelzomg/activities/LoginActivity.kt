package com.example.marvelzomg.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.marvelzomg.R
import com.example.marvelzomg.services.FireBaseService


class LoginActivity : AppCompatActivity() {

    var emailEditText: EditText? = null
    var passwordEditText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        emailEditText = findViewById(R.id.input_email)
        passwordEditText = findViewById(R.id.input_password)
        FireBaseService.getOnlineUsers()
    }

    override fun onDestroy() {
        super.onDestroy()
        FireBaseService.toggleOnline(false)
    }

    fun login(view: View) {
        if (!emailEditText!!.text.isEmpty() && !passwordEditText!!.text.isEmpty()) {
            FireBaseService.signIn(emailEditText!!.text.toString(), passwordEditText!!.text.toString(), this)
        } else {
            Toast.makeText(this, "Please fill up the Credentials :|", Toast.LENGTH_SHORT).show()
        }
    }

    fun linkRegister(view: View) {
        startActivity(Intent(this, RegisterActivity::class.java))
    }


}
