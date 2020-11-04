package com.dojo.coroutines.data.local

import com.dojo.coroutines.data.model.MoviesResponse.Movie

interface MoviesLocalDataSource {

    suspend fun getPopularMovies(
        page: Int,
        result: Result<List<Movie>>.() -> Unit
    )

    suspend fun getNowPlayingMovies(
        page: Int,
        result: Result<List<Movie>>.() -> Unit
    )

    suspend fun savePopularMovies(
        page: Int,
        movies: List<Movie>,
        result: Result<Unit>.() -> Unit
    )

    suspend fun saveNowPlayingMovies(
        page: Int,
        movies: List<Movie>,
        result: Result<Unit>.() -> Unit
    )

    suspend fun clear()

}