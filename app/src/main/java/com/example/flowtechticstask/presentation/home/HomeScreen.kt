package com.example.flowtechticstask.presentation.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.flowtechticstask.LocalNavController
import com.example.flowtechticstask.R
import com.example.flowtechticstask.presentation.composables.HomeCharacterCard
import com.example.flowtechticstask.presentation.composables.SnackBar
import com.example.flowtechticstask.presentation.composables.calculateSize
import com.example.flowtechticstask.presentation.navigation.navigateToLogIn
import com.example.flowtechticstask.ui.theme.Space8
import com.example.flowtechticstask.ui.theme.lightBackground
import com.example.flowtechticstask.ui.theme.lightPrimary
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val navController = LocalNavController.current
    val state by viewModel.state.collectAsState()
    val listState = rememberLazyStaggeredGridState()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    HomeContent(state, viewModel, listState, drawerState)
    LaunchedEffect(key1 = true) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is HomeUiEffect.NavigateToLogin -> navController.navigateToLogIn()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, FlowPreview::class)
@Composable
private fun HomeContent(
    state: HomeUiState,
    interaction: HomeInteraction,
    listState: LazyStaggeredGridState,
    drawerState: DrawerState,
) {
    val coroutineScope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                NavigationDrawerItem(
                    label = { Text(text = stringResource(id = R.string.logout)) },
                    selected = false,
                    onClick = interaction::onLogOutClick
                )
            }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(text = "Rick and Morty") }, navigationIcon = {
                    IconButton(onClick = { coroutineScope.launch { drawerState.open() } }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = null)
                    }
                })
            }, modifier = Modifier
                .systemBarsPadding()
                .background(lightBackground)
        ) {
            PullToRefreshBox(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                isRefreshing = state.isScreenRefreshing,
                onRefresh = interaction::refreshScreenData
            ) {
                if (state.isScreenLoading)
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = lightPrimary
                    )
                else
                    LazyVerticalStaggeredGrid(
                        columns = StaggeredGridCells.Fixed(2),
                        state = listState,
                        modifier = Modifier
                            .fillMaxSize()
                            .systemBarsPadding()
                            .padding(horizontal = Space8),
                        verticalItemSpacing = Space8,
                        horizontalArrangement = Arrangement.spacedBy(Space8),
                    ) {
                        items(state.characters.size) { index ->
                            val height = calculateSize(index)
                            HomeCharacterCard(state.characters[index], height)
                        }
                        item(span = StaggeredGridItemSpan.FullLine) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(),
                                contentAlignment = Alignment.Center
                            ) {
                                if (state.isPageRefreshing)
                                    CircularProgressIndicator(
                                        color = lightPrimary,
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                            }
                        }
                    }
                state.messageResource?.let {
                    SnackBar(
                        text = stringResource(id = it),
                        modifier = Modifier.align(Alignment.BottomCenter)
                    ) {
                        interaction.clearMessage()
                    }
                }
                if (!state.hasInternet && state.characters.isEmpty())
                    Text(
                        text = stringResource(id = R.string.noInternetConnection),
                        modifier = Modifier.align(
                            Alignment.Center
                        )
                    )
            }
        }
    }
    LaunchedEffect(key1 = state) {
        Log.d("HomeScreen", state.toString())
    }
    LaunchedEffect(key1 = listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo }
            .debounce(1000)
            .collect { visibleItems ->
                val totalItems = listState.layoutInfo.totalItemsCount
                if (state.hasInternet && visibleItems.isNotEmpty() && visibleItems.last().index == totalItems - 1 && !state.isPageRefreshing) {
                    interaction.refreshData()
                }
            }
    }
}