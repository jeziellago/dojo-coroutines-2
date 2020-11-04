package com.dojo.coroutines.data.model

import com.dojo.coroutines.data.cache.Cacheable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponse<T>(
    @SerialName("page")
    val id: Long,

    @SerialName("total_pages")
    val totalPages: Int,

    @SerialName("results")
    val results: T
) {

    @Serializable
    data class Movie(
        @SerialName("id")
        val id: Long,

        @SerialName("title")
        val title: String,

        @SerialName("overview")
        val description: String,

        @SerialName("poster_path")
        val posterPath: String? = "",

        @SerialName("backdrop_path")
        val backdropPath: String? = "",

        @SerialName("vote_average")
        val votes: Float,

        @SerialName("release_date")
        val releaseDate: String,

        @SerialName("genre_ids")
        val genreIds: List<Int>
    ): Cacheable
}