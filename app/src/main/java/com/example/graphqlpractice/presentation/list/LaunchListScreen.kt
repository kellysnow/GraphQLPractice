package com.example.graphqlpractice.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.graphqlpractice.R
import com.example.graphqlpractice.domain.model.Launch

@Composable
fun LaunchList(
    onLaunchClick: (launchId: String) -> Unit,
    viewModel: LaunchListViewModel = hiltViewModel()
) {
    val state: ListUiState by viewModel.uiState.collectAsStateWithLifecycle()
    var cursor: String? by remember { mutableStateOf(null) }
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(items = state.list) { launch ->
            LaunchItem(launch = launch, onClick = onLaunchClick)
        }


        item {
            if (state.hasMore) {
                LoadingItem()
                viewModel.getList(state.cursor)
            }
        }
    }
}

@Composable
private fun LaunchItem(launch: Launch, onClick: (launchId: String) -> Unit) {
    ListItem(
        modifier = Modifier.clickable { onClick(launch.id) },
        headlineContent = {
            // Mission name
            Text(text = launch.name)
        },
        supportingContent = {
            // Site
            Text(text = launch.site ?: "")
        },
        leadingContent = {
            // Mission patch
            AsyncImage(
                modifier = Modifier.size(68.dp, 68.dp),
                model = launch.imageUrl,
                placeholder = painterResource(R.drawable.ic_placeholder),
                error = painterResource(R.drawable.ic_placeholder),
                contentDescription = "Mission patch"
            )
        }
    )
}

@Composable
private fun LoadingItem() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        CircularProgressIndicator()
    }
}