package com.example.marvelzomg.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.EditText
import com.example.marvelzomg.R
import com.example.marvelzomg.adapters.CharacterListAdapter
import com.example.marvelzomg.api.RetroFit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.character_list_view.*

class CharacterListActivity : AppCompatActivity() {

    val disposable = CompositeDisposable()
    val adapter = CharacterListAdapter(this)
    val layoutManager = LinearLayoutManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.character_list_view)

        characterListView.adapter = adapter
        characterListView.layoutManager = layoutManager

        findAllCharacters()

    }

    fun searchByName(view: View) {

        val searchText = findViewById<EditText>(R.id.searchCharacterName)

        characterListView.adapter = adapter
        characterListView.layoutManager = layoutManager

        if (!searchText!!.text.isEmpty()) {
            disposable.add(RetroFit.service.getAllCharactersByName(searchText.text.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { wrapper ->
                    adapter.characters = wrapper.data.results
                    adapter.notifyDataSetChanged()
                })
        } else {
            findAllCharacters()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    private fun findAllCharacters() {
        disposable.add(RetroFit.service.getAllCharacters()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { wrapper ->
                adapter.characters = wrapper.data.results
                adapter.notifyDataSetChanged()
            })
    }
}
