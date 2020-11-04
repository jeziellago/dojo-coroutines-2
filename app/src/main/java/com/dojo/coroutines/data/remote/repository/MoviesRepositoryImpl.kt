package com.dojo.coroutines.data.remote.repository

import com.dojo.coroutines.data.local.MoviesLocalDataSource
import com.dojo.coroutines.data.model.MoviesResponse
import com.dojo.coroutines.data.remote.datasource.MoviesRemoteDataSource
import com.dojo.coroutines.domain.entities.Genre
import com.dojo.coroutines.domain.entities.Movie
import com.dojo.coroutines.domain.repositories.GenreRepository
import com.dojo.coroutines.domain.repositories.MoviesRepository

internal class MoviesRepositoryImpl(
    private val moviesRemoteDataSource: MoviesRemoteDataSource,
    private val moviesLocalDataSource: MoviesLocalDataSource,
    private val genreRepository: GenreRepository
) : MoviesRepository {

    override suspend fun getPopularMovies(page: Int): List<Movie> {
        var popularMovies: List<MoviesResponse.Movie>? = null
        moviesLocalDataSource.getPopularMovies(page) {
            popularMovies = getOrNull()
        }
        val movies: List<MoviesResponse.Movie> = popularMovies?.takeIf { it.isNullOrEmpty().not() } ?: getRemote(page)
        return movies.map {
            it.mapToDomain()
        }
    }

    suspend fun getRemote(page: Int): List<MoviesResponse.Movie> {
        val popularMovies = moviesRemoteDataSource.getPopularMovies(page)
        moviesLocalDataSource.savePopularMovies(page, popularMovies) { }
        return popularMovies
    }

    override suspend fun getNowPlayingMovies(page: Int): List<Movie> {
        return moviesRemoteDataSource.getNowPlayingMovies(page).map {
            Movie(
                title = it.title,
                votes = it.votes,
                posterUrl = it.backdropPath ?: "",
                genres =  genreRepository.getGenre(it.genreIds)
            )
        }
    }

    private suspend fun MoviesResponse.Movie.mapToDomain() = Movie(
        title = title,
        votes = votes,
        posterUrl = backdropPath ?: "",
        genres =  genreRepository.getGenre(genreIds)
    )
}