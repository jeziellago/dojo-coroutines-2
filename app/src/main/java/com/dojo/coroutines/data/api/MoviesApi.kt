package com.dojo.coroutines.data.api

import com.dojo.coroutines.data.model.GenresResponse
import com.dojo.coroutines.data.model.MoviesResponse

interface MoviesApi {

    suspend fun getPopularMovies(page: Int): MoviesResponse<List<MoviesResponse.Movie>>

    suspend fun getNowPlayingMovies(page: Int): MoviesResponse<List<MoviesResponse.Movie>>

    suspend fun getGenres(): GenresResponse
}