package com.example.flowtechticstask.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier

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
