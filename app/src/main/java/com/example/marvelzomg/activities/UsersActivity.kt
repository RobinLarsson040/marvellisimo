package com.example.marvelzomg.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.marvelzomg.R
import com.example.marvelzomg.models.User
import com.example.marvelzomg.services.FireBaseService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_users.*

class UsersActivity : AppCompatActivity() {

    val disposable = CompositeDisposable()

    private var mAuth: FirebaseAuth? = null
    var users = arrayListOf<User>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        val adapter = UserAdapter(users, this)
        val layoutManager = LinearLayoutManager(this)

        rv_users_list.adapter = adapter
        rv_users_list.layoutManager = layoutManager

        users = FireBaseService.getOnlineUsers() as ArrayList<User>



        println(users.size)
    }
}
