package com.dojo.coroutines.data.local

import com.dojo.coroutines.data.model.GenresResponse

interface GenresLocalDataSource {

    suspend fun getGenres(result: Result<GenresResponse>.() -> Unit)

    suspend fun saveGenres(genres: GenresResponse, result: Result<Unit>.() -> Unit)

    suspend fun clear()
}