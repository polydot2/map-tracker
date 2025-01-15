package com.poly.herewego.ui.category

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CategoryScreen(category: String, onOpenDetails: (place: String) -> Unit) {

    var list = listOf(Pair("Tour de lu", false), Pair("Chateau des ducs", true), Pair("Elephant \uD83D\uDC18", false), Pair("Jardin des plantes", true))

    Column {
        Text("Category $category", style = MaterialTheme.typography.headlineLarge, modifier = Modifier.padding(bottom = 12.dp))
        Text("Liste des categories pour $category")
        Box(Modifier.height(12.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(items = list, itemContent = { PlaceItem(it.first, it.second, onOpenDetails) })
        }
    }
}

@Composable
fun PlaceItem(name: String, checked: Boolean, onClick: (place: String) -> Unit) {
    Card(
        modifier = Modifier
            .height(64.dp)
            .fillMaxWidth()
            .clickable(onClick = { onClick(name) })
    ) {
        Row(Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween, Alignment.CenterVertically) {
            Text(name, modifier = Modifier.padding(12.dp))
            Checkbox(checked = checked, enabled = false, onCheckedChange = {})
        }
    }
}

@Preview
@Composable
fun SimpleComposablePreview() {
    CategoryScreen("Nantes", {})
}
