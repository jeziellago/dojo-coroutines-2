package com.dojo.coroutines.domain.usecase

import com.dojo.coroutines.domain.entities.Movie
import com.dojo.coroutines.domain.repositories.MoviesRepository

class GetNowPlayingMoviesUseCase(
    private val moviesRepository: MoviesRepository
) {

    suspend operator fun invoke(page: Int): List<Movie> {
        return moviesRepository.getNowPlayingMovies(page)
    }
}