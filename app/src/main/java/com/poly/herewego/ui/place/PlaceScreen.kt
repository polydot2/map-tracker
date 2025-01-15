package com.poly.herewego.ui.place

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage


@Composable
fun PlaceScreen(id: String) {
    Column {
        Text("Detail place", style = MaterialTheme.typography.headlineLarge, modifier = Modifier.padding(bottom = 12.dp))

        AsyncImage(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape),
            model = "https://picsum.photos/200/300",
            contentDescription = "Translated description of what the image contains"
        )
        Text("Avez vous été ici ? $id")
        Checkbox(false, onCheckedChange = {})
    }
}

@Preview
@Composable
fun SimpleComposablePreview() {
    PlaceScreen("Tour LU")
}
