package com.example.flowtechticstask.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.flowtechticstask.presentation.home.CharacterUiModel
import com.example.flowtechticstask.utils.shimmerEffect

@Composable
fun HomeCharacterCard(character: CharacterUiModel, height: Dp) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        AsyncImage(
            model = character.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(90.dp)
                .height(height)
                .clip(RoundedCornerShape(8.dp))
//                .shimmerEffect(true)
        )
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = " - " + character.name)
            Text(text = " - " + character.species)
            Text(text = " - " + character.status)
        }
    }
}

@Preview
@Composable
private fun Preview() {
    HomeCharacterCard(
        CharacterUiModel(
            1,
            "Rick",
            "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            "Alive",
            "Human",
        ),
        170.dp
    )
}