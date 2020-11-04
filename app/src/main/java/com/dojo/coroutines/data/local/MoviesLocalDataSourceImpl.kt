package com.dojo.coroutines.data.local

import com.dojo.coroutines.data.cache.Cache
import com.dojo.coroutines.data.model.MoviesResponse
import com.kachej.CacheableList

class MoviesLocalDataSourceImpl(
    private val cache: Cache
) : MoviesLocalDataSource {

    override suspend fun getPopularMovies(
        page: Int,
        result: Result<List<MoviesResponse.Movie>>.() -> Unit
    ) {
        cache.get<CacheableList<MoviesResponse.Movie>>("popular_movies_$page") {
            onSuccess {
                result(Result.success(it.items.toList()))
            }
            onFailure { result(Result.failure(it)) }
        }
    }

    override suspend fun getNowPlayingMovies(
        page: Int,
        result: Result<List<MoviesResponse.Movie>>.() -> Unit
    ) {
        cache.get<CacheableList<MoviesResponse.Movie>>("now_playing_movies_$page") {
            onSuccess {
                result(Result.success(it.items.toList()))
            }
            onFailure { result(Result.failure(it)) }
        }
    }

    override suspend fun savePopularMovies(
        page: Int,
        movies: List<MoviesResponse.Movie>,
        result: Result<Unit>.() -> Unit
    ) {
        cache.put("popular_movies_$page", CacheableList(movies), result)
    }

    override suspend fun saveNowPlayingMovies(
        page: Int,
        movies: List<MoviesResponse.Movie>,
        result: Result<Unit>.() -> Unit
    ) {
        cache.put("now_playing_movies_$page", CacheableList(movies), result)
    }

    override suspend fun clear() {
        cache.clear()
    }

}