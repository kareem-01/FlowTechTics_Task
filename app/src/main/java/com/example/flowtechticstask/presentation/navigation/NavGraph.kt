package com.example.flowtechticstask.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.flowtechticstask.AuthViewModel
import com.example.flowtechticstask.ui.theme.lightPrimary

@Composable
fun NavGraph(navController: NavHostController, authViewModel: AuthViewModel = hiltViewModel()) {
    val isUserLoggedIn by authViewModel.isUserLoggedIn.collectAsState()
    if (isUserLoggedIn != null) {
        NavHost(
            navController = navController,
            startDestination = if (isUserLoggedIn as Boolean) Screen.Home.route else Screen.LogIn.route
        ) {
            logIn()
            signUp()
            home()
        }
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = lightPrimary)
        }
    }
}