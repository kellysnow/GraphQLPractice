package com.example.graphqlpractice.domain.model


data class LaunchList(
    val cursor: String?,
    val list: List<Launch>,
    val hasMore: Boolean
)
