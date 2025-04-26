package com.example.graphqlpractice.domain

import com.example.graphqlpractice.domain.model.LaunchList

interface NetworkRepository {
    suspend fun getList(cursor: String?): LaunchList
}