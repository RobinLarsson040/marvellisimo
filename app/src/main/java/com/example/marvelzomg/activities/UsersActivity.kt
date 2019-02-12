package com.example.marvelzomg.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.marvelzomg.R
import com.example.marvelzomg.models.User
import com.example.marvelzomg.services.FireBaseService
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_users.*

class UsersActivity : AppCompatActivity() {


    private var mAuth: FirebaseAuth? = null
    var users = arrayListOf<User>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        val adapter = UserAdapter(users, this)
        val layoutManager = LinearLayoutManager(this)
        rv_users_list.adapter = adapter
        rv_users_list.layoutManager = layoutManager as RecyclerView.LayoutManager?

        users = FireBaseService.getOnlineUsers() as ArrayList<User>

        println("SIZE FROM USERS-ACTIVITY: " + users.size)
        adapter.notifyDataSetChanged()
    }
}
