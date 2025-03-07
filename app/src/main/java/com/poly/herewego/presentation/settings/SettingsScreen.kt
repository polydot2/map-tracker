package com.poly.herewego.presentation.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen() {
    Column(
        Modifier
            .padding(12.dp)
    ) {
        Text("Settings", style = MaterialTheme.typography.headlineLarge)
        Box(Modifier.height(12.dp))
        SettingsButton("About", {})
        Box(Modifier.height(12.dp))
        SettingsButton("Logout", {})
    }
}

@Composable
fun SettingsButton(name: String, onClick: (place: String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clickable(onClick = { onClick(name) })
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(12.dp), horizontalArrangement = Arrangement.SpaceBetween, Alignment.CenterVertically) {
            Text(name)
            Icon(Icons.AutoMirrored.Outlined.KeyboardArrowRight, contentDescription = "icon")
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    SettingsScreen()
}
