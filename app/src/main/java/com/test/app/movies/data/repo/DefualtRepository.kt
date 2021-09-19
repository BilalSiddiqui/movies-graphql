package com.test.app.movies.data.repo

import android.content.Context
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.coroutines.toDeferred
import com.apollographql.apollo.exception.ApolloException
import com.test.app.movies.data.EndPoint
import com.test.app.movies.data.apollo.AuthorizationInterceptor
import com.test.app.movies.data.model.GetAllMoviesQuery
import com.test.app.movies.data.model.Movie
import com.test.app.movies.data.model.Result
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
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
                it.node.releaseDate as? String,it.node.seasons) })
        }

    }

    override suspend fun addMovie(movie: Movie) {
        TODO("Not yet implemented")
    }
}