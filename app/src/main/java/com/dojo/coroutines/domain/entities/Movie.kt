package com.dojo.coroutines.domain.entities

data class Movie(
    val title: String,
    val votes: Float,
    val posterUrl: String,
    val genres: List<Genre>
)