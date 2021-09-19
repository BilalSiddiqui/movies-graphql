package com.test.app.movies.di

import android.content.Context
import com.apollographql.apollo.ApolloClient
import com.test.app.movies.data.EndPoint
import com.test.app.movies.data.apollo.AuthorizationInterceptor
import com.test.app.movies.data.repo.DefaultRepository
import com.test.app.movies.data.repo.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

        @Singleton
        @Provides
         fun provideApolloClient(@ApplicationContext context:Context): ApolloClient {
           return ApolloClient.builder()
                .serverUrl(EndPoint.URL_GRAPHQL)
                .okHttpClient(
                    OkHttpClient.Builder()
                        .addInterceptor(AuthorizationInterceptor(context))
                        .build()
                )
                .build()

        }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {
    @Singleton
    @Binds
    abstract fun bindDefaultRepository(
        chatRepoImp: DefaultRepository
    ): MovieRepository
}