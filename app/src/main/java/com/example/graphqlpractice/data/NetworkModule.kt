package com.example.graphqlpractice.data

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.network.http.LoggingInterceptor
import com.example.graphqlpractice.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    private val serverUrl = "https://apollo-fullstack-tutorial.herokuapp.com/graphql"


    @Provides
    @Singleton
    fun providesGraphQLClient(

    ): ApolloClient {
        return ApolloClient.Builder().serverUrl(serverUrl)
            .addHttpInterceptor(LoggingInterceptor(level = if (BuildConfig.DEBUG) LoggingInterceptor.Level.BODY else LoggingInterceptor.Level.NONE))
            .build()
    }
}