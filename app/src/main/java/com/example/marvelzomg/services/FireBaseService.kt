package com.example.marvelzomg.services

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.marvelzomg.activities.HomeActivity
import com.example.marvelzomg.models.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class FireBaseService {

    companion object {
        private var database: DatabaseReference = FirebaseDatabase.getInstance().reference
        private var auth: FirebaseAuth = FirebaseAuth.getInstance()
        private var currentUser: FirebaseUser? = null
        private lateinit var usersRef: DatabaseReference

        fun createUser(email: String, password: String, context: Context) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task: Task<AuthResult> ->
                    if (task.isSuccessful) {
                        currentUser = auth.currentUser
                        usersRef = database.child("users").child(currentUser!!.uid)
                        addUserDetails(currentUser?.email!!)
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

        fun signIn(email: String, password: String, context: Context) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task: Task<AuthResult> ->
                    if (task.isSuccessful) {
                        currentUser = auth.currentUser
                        usersRef = database.child("users").child(currentUser!!.uid)
                        toggleOnline(true)
                        val intent = Intent(context, HomeActivity::class.java)
                        context.startActivity(intent)
                    } else {
                        Toast.makeText(
                            context, "Login Failed :( " + task.exception,
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }
        }


        private fun addUserDetails(email: String) {
            val user = User(email)
            usersRef.setValue(user)
            toggleOnline(true)
        }

        fun toggleOnline(status: Boolean) {
            if (currentUser != null)
                usersRef.child("online").setValue(status)
        }

        fun signOut() {
            toggleOnline(false)
            auth.signOut()
        }

        fun getOnlineUsers(): MutableList<User> {
            val users: MutableList<User> = mutableListOf()

            val ref = database.child("users")

            ref.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    println("USERS ONLINE STATUS CHANGED")


                    snapshot.children.mapNotNullTo(users) {
                        it.getValue<User>(User::class.java)
                    }
                    println(users.toString())
                }
            })
            return users
        }
    }
}




