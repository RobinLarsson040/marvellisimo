package com.example.marvelzomg.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.marvelzomg.R
import com.example.marvelzomg.adapters.CharacterListAdapter
import com.example.marvelzomg.api.RetroFit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.character_list_view.*

class CharacterListActivity : AppCompatActivity() {

    val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_list_view)


        val adapter = CharacterListAdapter(this)
        val layoutManager = LinearLayoutManager(this)


        characterListView.adapter = adapter
        characterListView.layoutManager = layoutManager



        disposable.add(RetroFit.service.getAllCharacters()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { wrapper ->
                adapter.characters = wrapper.data.results
                adapter.notifyDataSetChanged()
            })

    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }
}


/* */