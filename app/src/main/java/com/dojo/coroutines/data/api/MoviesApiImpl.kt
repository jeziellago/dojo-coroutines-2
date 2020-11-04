package com.dojo.coroutines.data.api

import com.dojo.coroutines.data.model.GenresResponse
import com.dojo.coroutines.data.model.MoviesResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get

private const val API_URL = "https://api.themoviedb.org/3/"

internal class MoviesApiImpl(
    private val httpClient: HttpClient,
    private val apiUrl: String = API_URL
) : MoviesApi {

    override suspend fun getPopularMovies(page: Int): MoviesResponse<List<MoviesResponse.Movie>> {
        return httpClient.get(urlFrom("movie/popular", "page=$page"))
    }

    override suspend fun getNowPlayingMovies(page: Int): MoviesResponse<List<MoviesResponse.Movie>> {
        return httpClient.get(urlFrom("movie/now_playing", "page=$page"))
    }

    override suspend fun getGenres(): GenresResponse {
        return httpClient.get(urlFrom("genre/movie/list"))
    }

    private fun urlFrom(endpoint: String, query: String = ""): String {
        val url = "$apiUrl${endpoint}?language=en-US&api_key=a4658479bb1148057cefb67dd42387f3"
        return if (query.isNotEmpty()) "$url&$query" else url
    }

}