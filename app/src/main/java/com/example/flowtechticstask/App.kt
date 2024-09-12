package com.example.flowtechticstask

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.flowtechticstask.presentation.navigation.NavGraph

val LocalNavController = compositionLocalOf<NavHostController> { error("NO NavController") }

@Composable
fun App() {
    val navController = rememberNavController()
    CompositionLocalProvider(LocalNavController provides navController) {
        NavGraph(navController)
    }
}