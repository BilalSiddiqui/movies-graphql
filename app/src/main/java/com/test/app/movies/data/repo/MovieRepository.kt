package com.test.app.movies.data.repo

import com.test.app.movies.data.model.Movie
import com.test.app.movies.data.model.Result

interface MovieRepository {
    suspend fun getListOfMovies(pageNo:Int): Result<List<Movie>>
    suspend fun addMovie(movie: Movie)
}