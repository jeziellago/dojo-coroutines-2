package com.dojo.coroutines

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.dojo.coroutines.presentation.MoviesViewModel
import com.dojo.coroutines.view.MoviesAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoviesActivity : AppCompatActivity() {

    private val moviesViewModel by viewModel<MoviesViewModel>()
    private val popularMoviesAdapter by lazy { MoviesAdapter { moviesViewModel.onNextPagePopularMovies() } }
    private val nowPlayingMoviesAdapter by lazy { MoviesAdapter { moviesViewModel.onNextPageNowPlayingMovies() } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViews()
        setupStateObserver()
    }

    private fun setupStateObserver() {
        moviesViewModel.getMovies().observe(this) { listMovies ->
            popularMoviesAdapter.submitList(listMovies)
        }

        moviesViewModel.getNowMovies().observe(this){ listNowMovies ->
            nowPlayingMoviesAdapter.submitList(listNowMovies)
        }
    }

    private fun setupViews() {
        with(rvPopularMovies) {
            layoutManager = LinearLayoutManager(this@MoviesActivity, HORIZONTAL, false)
            adapter = popularMoviesAdapter
        }
        with(rvNowPlayingMovies) {
            layoutManager = LinearLayoutManager(this@MoviesActivity, HORIZONTAL, false)
            adapter = nowPlayingMoviesAdapter
        }
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoadingPopularMovies() {
        progressPopularMovies.isVisible = true
    }

    private fun showLoadingNowPlayingMovies() {
        progressNowPlayingMovies.isVisible = true
    }

    private fun hideLoadingPopularMovies() {
        progressPopularMovies.isVisible = false
    }

    private fun hideLoadingNowPlayingMovies() {
        progressNowPlayingMovies.isVisible = false
    }

}