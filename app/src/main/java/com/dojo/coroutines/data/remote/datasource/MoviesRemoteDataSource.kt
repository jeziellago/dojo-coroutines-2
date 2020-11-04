package com.dojo.coroutines.data.remote.datasource

import com.dojo.coroutines.data.model.MoviesResponse.Movie

interface MoviesRemoteDataSource {

    suspend fun getPopularMovies(page: Int): List<Movie>

    suspend fun getNowPlayingMovies(page: Int): List<Movie>

}