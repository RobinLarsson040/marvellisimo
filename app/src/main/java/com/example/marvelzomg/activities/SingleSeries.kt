package com.example.marvelzomg.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.marvelzomg.R
import com.example.marvelzomg.api.RetroFit
import com.example.marvelzomg.services.FireBaseService
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
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
