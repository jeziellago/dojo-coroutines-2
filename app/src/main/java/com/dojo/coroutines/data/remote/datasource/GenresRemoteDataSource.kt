package com.dojo.coroutines.data.remote.datasource

import com.dojo.coroutines.data.model.GenresResponse

interface GenresRemoteDataSource {

    suspend fun getGenres(): GenresResponse
}