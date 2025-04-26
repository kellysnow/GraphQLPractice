package com.example.graphqlpractice.domain.model

data class Launch(
    val id: String,
    val name: String,
    val site: String?,
    val imageUrl: String?,
)

