package com.example.marvelzomg.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
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


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_character)

        favoriteButton = findViewById(R.id.FavoriteButton)
        val favoriteCharacters = FireBaseService.favoriteCharacters

        disposable.add(
            RetroFit.service.getCharacterById(intent.action!!.toInt()).subscribeOn(Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()
            ).subscribe { character, error ->
                run {
                    if (error != null) {
                        println("ERROR")
                    } else {
                        this.character = character.data!!.results[0]
                        charName.text = character.data.results[0].name
                        singleDescription.text = character.data.results[0].description
                        Picasso.with(this)
                            .load(character.data.results[0].thumbnail!!.path + "." + character.data.results[0].thumbnail!!.extension)
                            .into(singleImage)
                        changeButtonStyle()
                    }
                }
            })
    }

    @SuppressLint("SetTextI18n")
    fun changeButtonStyle() {
        val favoriteCharacters = FireBaseService.favoriteCharacters
        if (favoriteCharacters.contains(character)) {
            favoriteButton!!.text = "REMOVE FAVORITE"
        } else {
            favoriteButton!!.text = "ADD FAVORITE"
        }
    }


    fun favorite(view: View) {
        val favoriteCharacters = FireBaseService.favoriteCharacters
        if (favoriteCharacters.contains(character)) {
            FireBaseService.removeFavorite(character!!.id, "Characters")
            Toast.makeText(this, "Added Favorite :D", Toast.LENGTH_SHORT).show()
        } else {
            FireBaseService.addFavorite(this.character!!, "Characters")
            Toast.makeText(this, "Removed Favorite :|", Toast.LENGTH_SHORT).show()
        }
        changeButtonStyle()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}
