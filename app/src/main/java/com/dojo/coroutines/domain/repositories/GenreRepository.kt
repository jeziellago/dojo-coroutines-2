package com.dojo.coroutines.domain.repositories

import com.dojo.coroutines.domain.entities.Genre

interface GenreRepository {

    suspend fun getGenres(): List<Genre>
}