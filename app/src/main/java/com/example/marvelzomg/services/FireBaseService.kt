package com.example.marvelzomg.services

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.marvelzomg.activities.HomeActivity
import com.example.marvelzomg.adapters.FavoriteCharactersAdapter
import com.example.marvelzomg.adapters.UserAdapter
import com.example.marvelzomg.models.Character
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
        var favoriteCharacters = arrayListOf<Character>()

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

        fun signOut() {
            toggleOnline(false)
            auth.signOut()
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

        fun addFavorite(character: Character, type: String) {
            usersRef.child("favorite$type/${character.id}").setValue(character)
        }

        fun removeFavorite(id: Int, type: String) {
            usersRef.child("favorite$type/$id").removeValue()
        }

        fun getOnlineUsers(users: ArrayList<User>, adapter: UserAdapter) {
            val ref = database.child("users")

            ref.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    println("USERS ONLINE STATUS CHANGED")

                    users.clear()

                    for (postSnapshot in snapshot.children) {
                        users.add(postSnapshot.getValue<User>(User::class.java)!!)
                    }
                    adapter.notifyDataSetChanged()
                }
            })
        }

        fun getFavoriteCharacters(characters: ArrayList<Character>, adapter: FavoriteCharactersAdapter) {
            val ref = database.child("favoriteCharacters")

            ref.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    characters.clear()
                    println("FAVORITE CHARACTERS")
                    for (postSnapshot in snapshot.children) {
                        println(postSnapshot.toString())
                        characters.add(postSnapshot.getValue<Character>(Character::class.java)!!)
                    }
                    adapter.notifyDataSetChanged()
                }
            })
        }

        fun updateUserFavoriteCharacters() {
            usersRef.child("favoriteCharacters").addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    favoriteCharacters.clear()
                    println("FETCH FAVORITE CHARACTERS")

                    for (postSnapshot in snapshot.children) {
                        favoriteCharacters.add(postSnapshot.getValue<Character>(Character::class.java)!!)
                    }
                }
            })
        }

        fun getUserFavoriteSeries() {
            usersRef.child("favoriteSeries").addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    println("USER favoriteSeries STATUS CHANGED")

                    for (postSnapshot in snapshot.children) {
                        println(postSnapshot.toString())
                    }
                }
            })
        }
    }
}




