package com.dojo.coroutines.di

import android.content.Context
import com.dojo.coroutines.data.api.Api
import com.dojo.coroutines.data.api.MoviesApi
import com.dojo.coroutines.data.api.MoviesApiImpl
import com.dojo.coroutines.data.cache.Cache
import com.dojo.coroutines.data.cache.CacheImpl
import com.dojo.coroutines.data.local.GenresLocalDataSource
import com.dojo.coroutines.data.local.GenresLocalDataSourceImpl
import com.dojo.coroutines.data.local.MoviesLocalDataSource
import com.dojo.coroutines.data.local.MoviesLocalDataSourceImpl
import com.dojo.coroutines.data.remote.datasource.GenresRemoteDataSource
import com.dojo.coroutines.data.remote.datasource.GenresRemoteDataSourceImpl
import com.dojo.coroutines.data.remote.datasource.MoviesRemoteDataSource
import com.dojo.coroutines.data.remote.datasource.MoviesRemoteDataSourceImpl
import com.dojo.coroutines.data.remote.repository.GenreRepositoryImpl
import com.dojo.coroutines.data.remote.repository.MoviesRepositoryImpl
import com.dojo.coroutines.domain.repositories.GenreRepository
import com.dojo.coroutines.domain.usecase.GetNowPlayingMoviesUseCase
import com.dojo.coroutines.domain.usecase.GetPopularMoviesUseCase
import com.dojo.coroutines.domain.repositories.MoviesRepository
import com.dojo.coroutines.presentation.MoviesViewModel
import com.kachej.Kachej
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.util.concurrent.TimeUnit

private val coreModule = module {
    single { Api.createHttpClient() }
    single<MoviesApi> { MoviesApiImpl(httpClient = get()) }
    single<Cache> { CacheImpl(Kachej(get<Context>().cacheDir, 1L, TimeUnit.DAYS)) }
}

private val genresModule = module {
    factory<GenresLocalDataSource> {
        GenresLocalDataSourceImpl(
            cache = get()
        )
    }
    factory<GenresRemoteDataSource> {
        GenresRemoteDataSourceImpl(
            moviesApi = get()
        )
    }
    factory<GenreRepository> {
        GenreRepositoryImpl(
            genresLocalDataSource = get(),
            genresRemoteDataSource = get()
        )
    }
}

private val moviesModule = module {
    factory<MoviesLocalDataSource> { MoviesLocalDataSourceImpl(cache = get()) }
    factory<MoviesRemoteDataSource> {
        MoviesRemoteDataSourceImpl(
            moviesApi = get()
        )
    }

    factory<MoviesRepository> {
        MoviesRepositoryImpl(
            moviesRemoteDataSource = get(),
            moviesLocalDataSource = get(),
            genreRepository = get()
        )
    }
    factory { GetPopularMoviesUseCase(moviesRepository = get()) }
    factory { GetNowPlayingMoviesUseCase(moviesRepository = get()) }
    viewModel {
        MoviesViewModel(
            nowPlayingMoviesUseCase = get(),
            popularMoviesUseCase = get()
        )
    }
}

val applicationModules = coreModule + genresModule + moviesModule