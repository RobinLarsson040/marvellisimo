package com.example.marvelzomg

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.content.Intent
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class LoginActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    private var database: FirebaseDatabase? = null
    private var myRef: DatabaseReference? = null
    private var currentUser: FirebaseUser? = null

    var emailEditText: EditText? = null
    var passwordEditText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        emailEditText = findViewById(R.id.input_email)
        passwordEditText = findViewById(R.id.input_password)

        database = FirebaseDatabase.getInstance()
        mAuth = FirebaseAuth.getInstance()
        myRef = database!!.getReference("online_users")
    }

    public override fun onStart() {
        super.onStart()
        currentUser = mAuth!!.getCurrentUser()
        if (currentUser != null) {
            myRef!!.setValue(currentUser!!.uid)
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }

    fun login(view: View) {
        if (!emailEditText!!.text.isEmpty() && !passwordEditText!!.text.isEmpty()) {
            this.mAuth!!.signInWithEmailAndPassword(emailEditText!!.text.toString(), passwordEditText!!.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        myRef!!.setValue(currentUser!!.uid)
                        startActivity(Intent(this, HomeActivity::class.java))
                        Toast.makeText(
                            this, "Successfully Logged in :)", Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(this, "Error Logging in :(", Toast.LENGTH_SHORT).show()
                    }
                }
        } else {
            Toast.makeText(this, "Please fill up the Credentials :|", Toast.LENGTH_SHORT).show()
        }
    }

    fun linkRegister(view: View) {
        startActivity(Intent(this, RegisterActivity::class.java))
    }


}
