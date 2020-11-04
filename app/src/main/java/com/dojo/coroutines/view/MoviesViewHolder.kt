package com.dojo.coroutines.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.dojo.coroutines.domain.entities.Movie
import kotlinx.android.synthetic.main.movie_item.view.*

class MoviesViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bindTo(movie: Movie) {
        with(view) {
            tvMovieTitle.text = movie.title
            tvMovieGenre.text = movie.genres.joinToString { it.name }
            tvMovieVotes.text = movie.votes.toString()
            imgMovie.load("https://image.tmdb.org/t/p/w500${movie.posterUrl}")
        }
    }
}