package com.odearmas.buscapelis.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.odearmas.buscapelis.R
import com.odearmas.buscapelis.adapter.PeliAdapter
import com.odearmas.buscapelis.data.PeliAPIService
import com.odearmas.buscapelis.data.PeliListResponse
import com.odearmas.buscapelis.data.PeliThumb
import com.odearmas.buscapelis.databinding.ActivityMainBinding
import com.odearmas.buscapelis.utils.RetrofitProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var peliListResponse: PeliListResponse
    lateinit var adapter: PeliAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = PeliAdapter { position -> navigateToDetail(peliListResponse.results[position])}

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)

        supportActionBar?.title=getString(R.string.movie_list)
        supportActionBar?.setBackgroundDrawable(getDrawable(R.color.lightbackgroundText))

        searchByName("Guardians")

    }

    private fun searchByName(query: String) {
        // Llamada en segundo plano
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val apiService = RetrofitProvider.getRetrofit().create(PeliAPIService::class.java)
                val result = apiService.searchPelisByName(query)
                if (result.results.isNotEmpty()) {
                    runOnUiThread {
                        peliListResponse = result
                        adapter.updateData(result.results)
                    }
                } else {
                    runOnUiThread {
                        adapter.updateData(emptyList())
                    }
                }
                Log.i("HTTP", "${result.results}")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_main_menu, menu)
        val searchViewItem = menu.findItem(R.id.menu_search)
        val searchView = searchViewItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(newText: String?): Boolean {

                searchViewItem.collapseActionView()
                if (newText != null) {
                    searchByName(newText)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //if (newText != null) {
                    //searchByName(newText)
                //}
                return false
            }

        })
        return true
    }

    private fun navigateToDetail(peliThumb: PeliThumb) {
        val callDetail: Intent = Intent(this, DetailActivity::class.java)
        callDetail.putExtra("MOVIE_ID", peliThumb.imdbID)
        startActivity(callDetail)
    }

}