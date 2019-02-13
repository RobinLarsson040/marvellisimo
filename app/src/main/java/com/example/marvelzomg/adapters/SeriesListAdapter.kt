package com.example.marvelzomg.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.marvelzomg.R
import com.example.marvelzomg.activities.SingleSeries
import com.example.marvelzomg.models.Series
import com.squareup.picasso.Picasso

class SeriesListAdapter(val context: Context) : RecyclerView.Adapter<SeriesListAdapter.Holder>() {

    var series = mutableListOf<Series>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {

        val view = LayoutInflater.from(context).inflate(R.layout.series_list_item, p0, false)

        return Holder(view)
    }

    override fun getItemCount(): Int {
        return series.count()
    }

    override fun onBindViewHolder(p0: Holder, p1: Int) {
        p0.bind(series[p1], context)
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val seriesImage = itemView.findViewById<ImageView>(R.id.seriesImage)
        val seriesName = itemView.findViewById<TextView>(R.id.seriesName)


        fun bind(series: Series, context: Context) {
            Picasso.with(context).load(series.thumbnail.path + "." + series.thumbnail.extension)
                .into(seriesImage)
            seriesName?.text = series.title

            itemView.setOnClickListener {
                val intent = Intent(series.id.toString(), null, context, SingleSeries::class.java)
                context.startActivity(intent)
            }
        }
    }

}