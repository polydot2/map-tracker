package com.poly.herewego.ui.login

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
fun LoginScreen(name: String, onLogin: () -> Unit) {
    Column {
        Text("Login", style = MaterialTheme.typography.headlineLarge)
        Box(Modifier.height(12.dp))
        Button(onClick = onLogin) {
            Text("Login")
        }
    }
}

