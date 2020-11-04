package com.dojo.coroutines.data.remote.repository

import com.dojo.coroutines.data.local.GenresLocalDataSource
import com.dojo.coroutines.data.remote.datasource.GenresRemoteDataSource
import com.dojo.coroutines.domain.entities.Genre
import com.dojo.coroutines.domain.repositories.GenreRepository

class GenreRepositoryImpl(
    private val genresRemoteDataSource: GenresRemoteDataSource,
    private val genresLocalDataSource: GenresLocalDataSource
) : GenreRepository {

    override suspend fun getGenres(): List<Genre> {
        TODO("Not yet implemented")
    }

}