package com.example.flowtechticstask.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.flowtechticstask.presentation.navigation.Screen

@Stable
fun Modifier.noRippleClick(onClick: () -> Unit): Modifier {
    val interaction = MutableInteractionSource()
    return this.then(
        Modifier.clickable(
            interactionSource = interaction,
            indication = null,
            onClick = onClick
        )
    )
}

fun NavController.clearBackStackAndNavigate(destination: Screen) {
    navigate(destination.route) {
        popUpTo(graph.startDestinationId) { inclusive = true }
        launchSingleTop = true
    }
}