package com.example.graphqlpractice.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graphqlpractice.data.NetworkRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchListViewModel @Inject constructor(private val networkRepository: NetworkRepositoryImpl) :
    ViewModel() {
    private val _uiState = MutableStateFlow(ListUiState())
    private val initialState = ListUiState(null, emptyList(), hasMore = false, isLoading = true)
    val uiState = _uiState.onStart {
        getList(null)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), initialState)


    fun getList(cursor: String?) {
        viewModelScope.launch {
            val response = networkRepository.getList(cursor)
            _uiState.update { it.copy(cursor = response.cursor, list = it.list + response.list, hasMore = response.hasMore) }
        }

    }
}