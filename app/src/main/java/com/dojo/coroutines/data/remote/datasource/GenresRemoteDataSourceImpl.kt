package com.dojo.coroutines.data.remote.datasource

import com.dojo.coroutines.data.api.MoviesApi
import com.dojo.coroutines.data.model.GenresResponse

class GenresRemoteDataSourceImpl(
    private val moviesApi: MoviesApi
) : GenresRemoteDataSource {

    override suspend fun getGenres(): GenresResponse = moviesApi.getGenres()
}