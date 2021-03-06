package com.example.marvelzomg.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.marvelzomg.R
import com.example.marvelzomg.activities.SingleCharacter
import com.example.marvelzomg.models.Character
import com.squareup.picasso.Picasso

class FavoriteCharactersAdapter(var characters: ArrayList<Character>, var context: Context) :
    RecyclerView.Adapter<FavoriteCharactersAdapter.Holder>() {
    override fun onBindViewHolder(p0: Holder, p1: Int) {
        p0.bind(characters[p1], context)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FavoriteCharactersAdapter.Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.character_list_item, p0, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return characters.count()
    }


    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val characterName = itemView.findViewById<TextView>(R.id.characterName)
        val characterImage = itemView.findViewById<ImageView>(R.id.characterImage)

        @SuppressLint("SetTextI18n")
        fun bind(character: Character, context: Context) {
            Picasso.with(context).load(character.thumbnail!!.path + "." + character.thumbnail.extension)
                .into(characterImage)
            characterName?.text = character.name

            itemView.setOnClickListener {
                val intent = Intent(character.id.toString(), null, context, SingleCharacter::class.java)
                context.startActivity(intent)
            }
        }
    }


}