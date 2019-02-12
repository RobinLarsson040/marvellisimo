package com.example.marvelzomg.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.marvelzomg.R
import com.example.marvelzomg.api.RetroFit
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_single_character.*

class SingleCharacter : AppCompatActivity() {

    val disposable = CompositeDisposable()

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
                        charName.text = character.data.results[0].name
                        singleDescription.text = character.data.results[0].description
                        Picasso.with(this)
                            .load(character.data.results[0].thumbnail.path + "." + character.data.results[0].thumbnail.extension)
                            .into(singleImage)
                    }
                }
            })

    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}
