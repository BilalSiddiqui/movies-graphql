package com.test.app.movies.data

import com.test.app.movies.data.model.Movie

interface MovieRepository {
    fun getListOfMovies(pageNo:Int)
    fun addMovie(movie: Movie)
}