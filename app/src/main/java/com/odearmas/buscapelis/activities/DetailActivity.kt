package com.odearmas.buscapelis.activities

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.odearmas.buscapelis.R
import com.odearmas.buscapelis.data.PeliAPIService
import com.odearmas.buscapelis.data.PeliResponse
import com.odearmas.buscapelis.databinding.ActivityDetailBinding
import com.odearmas.buscapelis.utils.RetrofitProvider
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {
    private lateinit var peli: PeliResponse
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Recibir la petición desde activity_main y construir la vista de detalle
        val peliId = intent.getStringExtra("MOVIE_ID")


            getPeliById(peliId!!)

        supportActionBar?.setDisplayHomeAsUpEnabled(true) //Flecha de ir atrás
        supportActionBar?.title=getString(R.string.back_from_detail)
        supportActionBar?.setBackgroundDrawable(getDrawable(R.color.lightbackgroundText))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun loadData() {

        Picasso.get().load(peli.imageURL).into(binding.posterImageView)
        binding.movieTitleTextView.text = peli.title
        binding.movieYearTextView.text = peli.year
        binding.movieRuntimeTextView.text = peli.runtime
        binding.movieDirectorTextView.text = peli.director
        binding.movieGenreTextView.text = peli.genre
        binding.movieCountryTextView.text = peli.country
        binding.moviePlotTextView.text = peli.plot

    }

    private fun getPeliById(query: String) {
        // Llamada en segundo plano
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val apiService = RetrofitProvider.getRetrofit().create(PeliAPIService::class.java)
                val result = apiService.searchPeliById(query)

                runOnUiThread {
                    peli = result
                    loadData()
                }

                // Log.i("HTTP", "${result.results}")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}