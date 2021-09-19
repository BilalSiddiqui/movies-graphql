package com.test.app.movies.data.repo

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.exception.ApolloException
import com.test.app.movies.data.model.*
import com.test.app.movies.data.model.type.CreateMovieFieldsInput
import com.test.app.movies.data.model.type.CreateMovieInput
import com.test.app.movies.utils.Utils
import javax.inject.Inject

/**
* Responsible for all data requirement of application
* */
class DefaultRepository @Inject constructor(val apolloClient: ApolloClient) : MovieRepository {


    override suspend fun getListOfMovies(pageNo: Int): Result<List<Movie>> {
        val response = try { apolloClient.query(GetAllMoviesQuery()).await()

        } catch (e: ApolloException) {
            // handle protocol errors
            return Result.Error("Something went wrong"+e.printStackTrace())
        }

        val launch = response.data
        if (launch == null || response.hasErrors()) {
            // handle application errors
            return Result.Error(response.errors?.first()?.message)
        }else{
            return Result.Success(launch.movies.edges?.map { Movie(it?.node?.id!!,it.node.title,
                Utils.changeServerDateToLocalDate(it.node.releaseDate as? String),it.node.seasons?.toInt()) })
        }

    }

    override suspend fun addMovie(movie: CreateMovieFieldsInput):Result<Movie> {
        val response = try { apolloClient.mutate(AddNewMovieMutation(CreateMovieInput(Input.optional(movie)))).await()

        } catch (e: ApolloException) {
            // handle protocol errors
            return Result.Error("Something went wrong"+e.printStackTrace())
        }

        val launch = response.data
        if (launch == null || response.hasErrors()) {
            // handle application errors
            return Result.Error(response.errors?.first()?.message)
        }else{
            return Result.Success(Movie(launch.createMovie?.movie?.id!!,launch.createMovie.movie.title, launch.createMovie.movie.releaseDate as? String?,launch.createMovie.movie.seasons?.toInt()))
        }

    }
}