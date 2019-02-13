package com.example.marvelzomg.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.example.marvelzomg.R
import com.example.marvelzomg.adapters.UserAdapter
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
        rv_users_list.layoutManager = layoutManager

        FireBaseService.getOnlineUsers(users, adapter)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val intent: Intent
        when (item!!.itemId) {
            R.id.action_character -> {
                intent = Intent(this, CharacterListActivity::class.java)
                this.startActivity(intent)
            }
            R.id.action_series -> {
                intent = Intent(this, SeriesListActivity::class.java)
                this.startActivity(intent)
            }
            R.id.action_users -> {
                intent = Intent(this, UsersActivity::class.java)
                this.startActivity(intent)
            }
            R.id.action_logout -> {
                FireBaseService.signOut()
                intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
        }

        return true

    }
}
