package com.dojo.coroutines.data.remote.datasource

import com.dojo.coroutines.data.model.MoviesResponse.Movie
import com.dojo.coroutines.data.api.MoviesApi

class MoviesRemoteDataSourceImpl(
    private val moviesApi: MoviesApi
) : MoviesRemoteDataSource {

    override suspend fun getPopularMovies(page: Int): List<Movie> {
        return moviesApi.getPopularMovies(page).results
    }

    override suspend fun getNowPlayingMovies(page: Int): List<Movie> {
        return moviesApi.getNowPlayingMovies(page).results
    }

}