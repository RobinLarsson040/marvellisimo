package com.example.marvelzomg.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.view.View
import android.widget.EditText


import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast
import android.content.Intent
import com.example.marvelzomg.R


class RegisterActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null

    var emailEditText: EditText? = null
    var passwordEditText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_activity)

        mAuth = FirebaseAuth.getInstance();

        emailEditText = findViewById(R.id.input_email)
        passwordEditText = findViewById(R.id.input_password)

    }

    fun register(view: View) {
        if (!emailEditText!!.text.isEmpty() && !passwordEditText!!.text.isEmpty()) {
            mAuth!!.createUserWithEmailAndPassword(emailEditText!!.text.toString(), passwordEditText!!.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = mAuth!!.getCurrentUser()
                        Toast.makeText(
                            this, "Successfully Logged in :)",
                            Toast.LENGTH_SHORT
                        ).show()

                        startActivity(Intent(this, HomeActivity::class.java))
                    } else {
                        Toast.makeText(
                            this, "Error register :( " + task.exception,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        } else {
            Toast.makeText(this, "Please fill up the Credentials :|", Toast.LENGTH_SHORT).show()
        }

    }

    fun linkLogin(view: View) {
        startActivity(Intent(this, LoginActivity::class.java))
    }

}
