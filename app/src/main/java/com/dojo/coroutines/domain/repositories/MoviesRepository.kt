package com.dojo.coroutines.domain.repositories

import com.dojo.coroutines.domain.entities.Movie

interface MoviesRepository {

    suspend fun getPopularMovies(page: Int): List<Movie>

    suspend fun getNowPlayingMovies(page: Int): List<Movie>
}