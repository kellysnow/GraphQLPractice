package com.example.graphqlpractice.data

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Optional
import com.example.graphqlpractice.LaunchListQuery
import com.example.graphqlpractice.domain.NetworkRepository
import com.example.graphqlpractice.domain.model.LaunchList
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(private val graphQLClient: ApolloClient) :
    NetworkRepository {
    override suspend fun getList(cursor: String?): LaunchList {
        val response = graphQLClient.query(LaunchListQuery(Optional.present(cursor))).execute()

        val launches = response.data?.launches
        return launches?.toLaunchList() ?: LaunchList(null, emptyList(), false)

    }
}