package com.dojo.coroutines.presentation

import androidx.lifecycle.ViewModel
import com.dojo.coroutines.domain.usecase.GetNowPlayingMoviesUseCase
import com.dojo.coroutines.domain.usecase.GetPopularMoviesUseCase

private const val FIRST_PAGE = 1

class MoviesViewModel(
    private val nowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val popularMoviesUseCase: GetPopularMoviesUseCase
) : ViewModel() {

    init {
        //TODO: load data from first page
    }

    private fun getPopularMovies(page: Int) {
        TODO("load movies and create")
    }

    private fun getNowPlayingMovies(page: Int) {
        TODO("load movies")
    }

    fun onNextPagePopularMovies() {
        TODO("load next page")
    }

    fun onNextPageNowPlayingMovies() {
        TODO("load next page")
    }
}