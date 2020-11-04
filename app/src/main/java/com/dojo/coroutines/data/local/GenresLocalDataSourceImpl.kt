package com.dojo.coroutines.data.local

import com.dojo.coroutines.data.cache.Cache
import com.dojo.coroutines.data.model.GenresResponse

class GenresLocalDataSourceImpl(
    private val cache: Cache
) : GenresLocalDataSource {

    override suspend fun getGenres(result: Result<GenresResponse>.() -> Unit) {
        cache.get("genres", result)
    }

    override suspend fun saveGenres(genres: GenresResponse, result: Result<Unit>.() -> Unit) {
        cache.put("genres", genres, result)
    }

    override suspend fun clear() {
        cache.clear("genres")
    }

}