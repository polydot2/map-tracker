package com.poly.herewego.ui.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(name: String, navigateToFriendProfile: (friendUserId: String) -> Unit) {
    Column(modifier = Modifier.padding(12.dp)) {
        Text("Settings", style = MaterialTheme.typography.headlineLarge)
        Box(Modifier.height(12.dp))
        Button(onClick = {}) {
            Text("Noter l'application")
        }
        Button(onClick = {}) {
            Text("Suggestions / retour")
        }
        Button(onClick = {}) {
            Text("A propos")
        }
        Box(Modifier.height(12.dp))
        Button(onClick = {}) {
            Text("Se déconnecter")
        }
    }
}