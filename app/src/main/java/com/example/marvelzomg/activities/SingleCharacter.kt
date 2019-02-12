package com.example.marvelzomg.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.marvelzomg.R
import com.example.marvelzomg.api.RetroFit
import com.example.marvelzomg.models.Character
import com.example.marvelzomg.models.CharacterDataWrapper
import com.squareup.picasso.Picasso
import io.reactivex.Single
import kotlinx.android.synthetic.main.activity_single_character.*
import kotlinx.android.synthetic.main.character_list_item.*

class SingleCharacter : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_character)


        val characterObject: Character
        val character = RetroFit.service.getCharacterById(intent.action!!.toInt()).cast(CharacterDataWrapper::class.java)
       // character.
        //val resultChar =

        //Picasso.with(this).load(resultChar.thumbnail.path+"."+resultChar.thumbnail.extension).into(characterImage)




    }
}
