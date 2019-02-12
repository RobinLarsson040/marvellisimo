package com.example.marvelzomg.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
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

        val searchText = findViewById<EditText>(R.id.searchCharacterName)

        if(adapter.characters.isEmpty()){
            findAllCharacters(0)
        }

        searchText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.characters.clear()
                adapter.notifyDataSetChanged()
                if(!searchText!!.text.isEmpty()){
                    findAllCharactersByName(searchText.text.toString(), adapter.characters.size)
                }else{
                    findAllCharacters(0)
                }
            }

        })

        characterListView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (recyclerView.canScrollVertically(1) && adapter.characters.size >= 20) {
                    if (!searchText!!.text.isEmpty()) {
                        findAllCharactersByName(searchText.text.toString(), adapter.characters.size)
                    } else {
                        findAllCharacters(adapter.characters.size)
                    }
                }
            }
        })


    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    private fun findAllCharacters(offset: Int) {
        disposable.add(RetroFit.service.getAllCharacters(offset)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { wrapper ->
                adapter.characters.addAll(wrapper.data.results)
                adapter.notifyDataSetChanged()
            })
    }

    private fun findAllCharactersByName(name: String, offset: Int) {
        disposable.add(RetroFit.service.getAllCharactersByName(name, offset)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { wrapper ->
                adapter.characters.addAll(wrapper.data.results)
                adapter.notifyDataSetChanged()
            })
    }


}
