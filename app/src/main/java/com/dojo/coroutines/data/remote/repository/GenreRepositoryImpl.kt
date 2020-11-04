package com.dojo.coroutines.data.remote.repository

import com.dojo.coroutines.data.local.GenresLocalDataSource
import com.dojo.coroutines.data.model.GenresResponse
import com.dojo.coroutines.data.remote.datasource.GenresRemoteDataSource
import com.dojo.coroutines.domain.entities.Genre
import com.dojo.coroutines.domain.repositories.GenreRepository

class GenreRepositoryImpl(
    private val genresRemoteDataSource: GenresRemoteDataSource,
    private val genresLocalDataSource: GenresLocalDataSource
) : GenreRepository {

    override suspend fun getGenres(): List<Genre> {
        return genresRemoteDataSource.getGenres().genres.map {
            Genre(it.id, it.name)
        }
    }

    override suspend fun getGenre(idsList: List<Int>): List<Genre> {
        return getGenres().filter { idsList.contains(it.id) }
    }
}