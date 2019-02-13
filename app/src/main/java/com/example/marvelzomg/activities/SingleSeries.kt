package com.example.marvelzomg.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.marvelzomg.R
import com.example.marvelzomg.api.RetroFit
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_single_character.*
import kotlinx.android.synthetic.main.activity_single_series.*

class SingleSeries : AppCompatActivity() {

    val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_series)


        disposable.add(
            RetroFit.service.getSeriesById(intent.action!!.toInt()).subscribeOn(Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()
            ).subscribe { serie, error ->
                run {
                    if (error != null) {
                    } else {
                        serieName.text = serie.data.results[0].title
                        singleSerieDescription.text = serie.data.results[0].description
                        Picasso.with(this)
                            .load(serie.data.results[0].thumbnail.path + "." + serie.data.results[0].thumbnail.extension)
                            .into(singleSerieImage)
                    }
                }
            })

    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}
