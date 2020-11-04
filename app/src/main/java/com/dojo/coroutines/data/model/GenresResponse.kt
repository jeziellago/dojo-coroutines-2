package com.dojo.coroutines.data.model

import com.dojo.coroutines.data.cache.Cacheable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenresResponse(
    @SerialName("genres")
    val genres: List<Genre>
): Cacheable {

    @Serializable
    data class Genre(
        @SerialName("id")
        val id: Int,

        @SerialName("name")
        val name: String
    )
}