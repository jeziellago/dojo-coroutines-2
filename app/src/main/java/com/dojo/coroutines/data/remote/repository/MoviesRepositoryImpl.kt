package com.dojo.coroutines.data.remote.repository

import com.dojo.coroutines.data.local.MoviesLocalDataSource
import com.dojo.coroutines.data.remote.datasource.MoviesRemoteDataSource
import com.dojo.coroutines.domain.entities.Movie
import com.dojo.coroutines.domain.repositories.GenreRepository
import com.dojo.coroutines.domain.repositories.MoviesRepository

internal class MoviesRepositoryImpl(
    private val moviesRemoteDataSource: MoviesRemoteDataSource,
    private val moviesLocalDataSource: MoviesLocalDataSource,
    private val genreRepository: GenreRepository
) : MoviesRepository {

    override suspend fun getPopularMovies(page: Int): List<Movie> {
        TODO("get from Local or Remote")
    }

    override suspend fun getNowPlayingMovies(page: Int): List<Movie> {
        TODO("get from Local or Remote")
    }

}