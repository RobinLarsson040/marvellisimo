package com.example.marvelzomg.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.example.marvelzomg.R
import com.example.marvelzomg.adapters.CharacterListAdapter
import com.example.marvelzomg.adapters.FavoriteCharactersAdapter
import com.example.marvelzomg.adapters.UserAdapter
import com.example.marvelzomg.models.Character
import com.example.marvelzomg.models.User
import com.example.marvelzomg.services.FireBaseService
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_users.*


class HomeActivity : AppCompatActivity() {

    private var usernameTextView: TextView? = null
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    var characters = arrayListOf<Character>()
    val adapter = FavoriteCharactersAdapter(characters, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val layoutManager = LinearLayoutManager(this)
        rv_favorite_characters.adapter = adapter
        rv_favorite_characters.layoutManager = layoutManager



        usernameTextView = findViewById(R.id.usernameTextView)
    }

    public override fun onStart() {
        super.onStart()
        FireBaseService.getFavoriteCharacters(characters, adapter)
        FireBaseService.updateUserFavoriteCharacters()
        if (auth.currentUser != null) {
            usernameTextView!!.text = auth.currentUser!!.email
        } else {
            usernameTextView!!.text = "NOT LOGGED IN :/"
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val intent: Intent
        when (item!!.itemId) {
            R.id.action_favorites -> {
                intent = Intent(this, HomeActivity::class.java)
                this.startActivity(intent)
            }
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
