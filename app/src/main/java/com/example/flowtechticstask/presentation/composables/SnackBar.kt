package com.example.flowtechticstask.presentation.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.flowtechticstask.ui.theme.Radius8
import com.example.flowtechticstask.ui.theme.Space8
import kotlinx.coroutines.delay

@Composable
fun SnackBar(modifier: Modifier = Modifier, text: String, onClear: () -> Unit = {}) {
    LaunchedEffect(key1 = Unit) {
        delay(3000)
        onClear()
    }
    Snackbar(
        modifier = modifier.padding(Space8),
        shape = RoundedCornerShape(Radius8),
        containerColor = Color.Black,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Space8)
        ) {
            Text(text = text, color = Color.White)
        }
    }
}


@Preview
@Composable
private fun Preview() {
    SnackBar(text = "KAREEM")
}