package com.example.graphqlpractice.presentation.list

import com.example.graphqlpractice.domain.model.Launch

data class ListUiState(
    val cursor: String? = null,
    val list: List<Launch> = emptyList(),
    val hasMore: Boolean = false,
    val isLoading: Boolean = false,
)
