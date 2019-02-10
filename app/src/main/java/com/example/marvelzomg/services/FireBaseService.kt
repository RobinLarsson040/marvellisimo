package com.example.marvelzomg.services

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import com.example.marvelzomg.activities.HomeActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FireBaseService {

    private var database: DatabaseReference = FirebaseDatabase.getInstance().reference
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    private var currentUser: FirebaseUser? = null
    private lateinit var usersRef: DatabaseReference


    fun createUser(email: String, password: String, firstName: String, lastName: String, context: Context) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    currentUser = auth.currentUser
                    usersRef = database.child("users").child(currentUser!!.uid)
                    addUserDetails(firstName, lastName, currentUser?.email!!)
                    Toast.makeText(
                        context, "Successfully Logged in :)",
                        Toast.LENGTH_SHORT
                    ).show()

                    val intent = Intent(context, HomeActivity::class.java)
                    context.startActivity(intent)
                } else {
                    Toast.makeText(
                        context, "Error register :( " + task.exception,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun addUserDetails(firstName: String, lastName: String, email: String) {
      //  val user = User(email, firstName, lastName)
     //   usersRef.setValue(user)
    }


}