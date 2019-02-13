package com.example.marvelzomg.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.example.marvelzomg.R
import com.example.marvelzomg.adapters.SeriesListAdapter
import com.example.marvelzomg.api.RetroFit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.series_list_view.*

class SeriesListActivity : AppCompatActivity() {

    val disposable = CompositeDisposable()
    val adapter = SeriesListAdapter(this)
    val layoutManager = LinearLayoutManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.series_list_view)
        seriesListView.adapter = adapter
        seriesListView.layoutManager = layoutManager

        val searchSeriesName = findViewById<EditText>(R.id.searchSeriesName)

        if(adapter.series.isEmpty()){
            findAllSeries(0)
        }

        searchSeriesName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                println("CHANGED YEAH")
                adapter.series.clear()
                adapter.notifyDataSetChanged()
                if(!searchSeriesName!!.text.isEmpty()){
                    findAllSeriesByName(searchSeriesName.text.toString(), adapter.series.size)
                }else{
                    findAllSeries(0)
                }
            }

        })

        seriesListView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (recyclerView.canScrollVertically(1) && adapter.series.size >= 20) {
                    if (!searchSeriesName!!.text.isEmpty()) {
                        findAllSeriesByName(searchSeriesName.text.toString(), adapter.series.size)
                    } else {
                        findAllSeries(adapter.series.size)
                    }
                }
            }
        })


    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    private fun findAllSeries(offset: Int) {
        disposable.add(
            RetroFit.service.getAllSeries(offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { wrapper ->
                    adapter.series.addAll(wrapper.data.results)
                    adapter.notifyDataSetChanged()
                })
    }

    private fun findAllSeriesByName(name: String, offset: Int) {
        disposable.add(
            RetroFit.service.getAllSeriesByTitle(name, offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { wrapper ->
                    adapter.series.addAll(wrapper.data.results)
                    adapter.notifyDataSetChanged()
                })
    }

}
