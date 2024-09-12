package com.example.flowtechticstask.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.example.flowtechticstask.ui.theme.Radius12
import com.example.flowtechticstask.ui.theme.Space16
import com.example.flowtechticstask.ui.theme.lightPrimary

@Composable
fun AuthenticationButton(text: String, onClick: () -> Unit, isLoading: Boolean = false) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(lightPrimary, shape = RoundedCornerShape(Radius12))
            .clip(RoundedCornerShape(Radius12))
            .clickable {
                onClick()
            }
    ) {
        if (isLoading) {
            CircularProgressIndicator(color = lightPrimary)
        } else
            Text(
                text = text, modifier = Modifier
                    .align(Alignment.Center)
                    .padding(vertical = Space16),
                color = Color.White
            )
    }
}