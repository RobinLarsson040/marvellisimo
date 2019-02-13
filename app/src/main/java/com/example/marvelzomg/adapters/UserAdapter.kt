package com.example.marvelzomg.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.marvelzomg.R
import com.example.marvelzomg.models.User

class UserAdapter(var users: ArrayList<User>, var context: Context) : RecyclerView.Adapter<UserAdapter.Holder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.user_list_item, p0, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return users.count()
    }

    override fun onBindViewHolder(p0: Holder, p1: Int) {
        p0.bind(users[p1], context)
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var email = itemView.findViewById<TextView>(R.id.userEmail)
        var online = itemView.findViewById<TextView>(R.id.userOnline)


        fun bind(user: User, context: Context) {
            email?.text = user.email
            online?.text = user.online.toString()

            println(user.email)

            /*itemView.setOnClickListener {
                val intent = Intent(character.id.toString(), null, context, SingleCharacter::class.java)
                context.startActivity(intent)
            }*/
        }

    }


}