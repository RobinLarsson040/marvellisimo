package com.example.marvelzomg.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.marvelzomg.R
import com.example.marvelzomg.api.RetroFit
import com.example.marvelzomg.models.Character
import com.example.marvelzomg.services.FireBaseService
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_single_character.*

class SingleCharacter : AppCompatActivity() {

    val disposable = CompositeDisposable()
    var favoriteButton: Button? = null
    var character: Character? = null
    val favoriteCharacters = FireBaseService.favoriteCharacters

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_character)

        disposable.add(
            RetroFit.service.getCharacterById(intent.action!!.toInt()).subscribeOn(Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()
            ).subscribe { character, error ->
                run {
                    if (error != null) {
                        println("ERROR")
                    } else {
                        this.character = character.data!!.results?.get(0)
                        charName.text = character.data.results?.get(0)!!.name
                        singleDescription.text = character.data.results[0].description
                        Picasso.with(this)
                            .load(character.data.results[0].thumbnail!!.path + "." + character.data.results[0].thumbnail!!.extension)
                            .into(singleImage)
                    }
                }
            })
    }

    fun favorite(view: View) {
        if (favoriteCharacters.contains(character)) {
            FireBaseService.removeFavorite(character!!.id, "Characters")
            Toast.makeText(this, "Removed Favorite :D", Toast.LENGTH_SHORT).show()
        } else {
            FireBaseService.addFavorite(this.character!!, "Characters")
            Toast.makeText(this, "Added Favorite :|", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
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
