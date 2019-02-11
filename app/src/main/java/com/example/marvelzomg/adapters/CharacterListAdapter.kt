package com.example.marvelzomg.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.marvelzomg.R
import com.example.marvelzomg.models.CharacterDataWrapper

class CharacterListAdapter(val context: Context, val characters: List<CharacterDataWrapper>) : RecyclerView.Adapter<CharacterListAdapter.Holder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.character_list_item, p0, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return characters.count()
    }

    override fun onBindViewHolder(p0: Holder, p1: Int) {
        p0.bind(characters[p1], context)
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val characterName = itemView.findViewById<TextView>(R.id.characterName)

        fun bind(character: CharacterDataWrapper, context: Context){

            characterName?.text = character.data.data[0].name

        }
    }
}