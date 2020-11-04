package com.dojo.coroutines.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dojo.coroutines.domain.entities.Genre
import com.dojo.coroutines.domain.entities.Movie
import com.dojo.coroutines.domain.usecase.GetNowPlayingMoviesUseCase
import com.dojo.coroutines.domain.usecase.GetPopularMoviesUseCase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import okhttp3.Dispatcher
import kotlin.coroutines.CoroutineContext

private const val FIRST_PAGE = 1

class MoviesViewModel(
    private val nowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val popularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {

    private val job = SupervisorJob()
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Main + job)

    private val mutableMovies = MutableLiveData<List<Movie>>()
    fun getMovies(): LiveData<List<Movie>> = mutableMovies

    private val mutableNowPlaying = MutableLiveData<List<Movie>>()
    fun getNowMovies(): LiveData<List<Movie>> = mutableNowPlaying

    private var currentNowPlayingPage = FIRST_PAGE
    private var currentPopularPage = FIRST_PAGE

    init {
        getPopularMovies()
        getNowPlayingMovies()
    }

    private fun getPopularMovies() {
        scope.launch {
            var movies: List<Movie>

            withContext(Dispatchers.IO) {
                movies = popularMoviesUseCase(currentPopularPage)
            }

            currentPopularPage++
            mutableMovies.value = movies
        }
    }

    private fun getNowPlayingMovies() {
        scope.launch {
            var movies: List<Movie>

            withContext(Dispatchers.IO) {
                movies = nowPlayingMoviesUseCase(currentNowPlayingPage)
            }

            currentNowPlayingPage++
            mutableMovies.value = movies
        }
    }

    fun onNextPagePopularMovies() {
        getPopularMovies()
    }

    fun onNextPageNowPlayingMovies() {
        getNowPlayingMovies()
    }

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }
}