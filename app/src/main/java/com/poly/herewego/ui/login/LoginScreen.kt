package com.poly.herewego.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(name: String, onLogin: () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column() {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 48.dp)
            ) {
                Text("Login", style = MaterialTheme.typography.headlineLarge)
            }
            Box(Modifier.height(12.dp))
            Card(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 48.dp)
            ) {
                Column(Modifier.padding(12.dp)) {
                    TextField("", label = { Text("Email") }, onValueChange = { })
                    TextField("", label = { Text("Password") }, onValueChange = { })

                    Box(Modifier.height(12.dp))
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                        Button(onClick = onLogin) {
                            Text("Login")
                        }
                    }
                }
            }
            Box(Modifier.height(12.dp))

            Card(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 48.dp)
            ) {
                Column(Modifier.padding(12.dp)) {
                    Text("Tester l'appli sans compte ? \nC'est ici !", fontStyle = FontStyle.Italic)
                    Box(Modifier.height(12.dp))
                    Button(modifier = Modifier.fillMaxWidth(), onClick = onLogin) {
                        Text("Preview mode")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    LoginScreen("Tour LU", {})
}
