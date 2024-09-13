package com.example.flowtechticstask.presentation.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun calculateSize(index: Int, sizes: List<Dp> =  listOf(130.dp, 170.dp)): Dp {
    val rowSizeIndex = index % sizes.size
    val colSizeIndex = index / sizes.size
    return if (colSizeIndex % 2 == 0)
        sizes[rowSizeIndex]
    else
        sizes.reversed()[rowSizeIndex]
}