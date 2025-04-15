package com.poly.herewego.presentation.widgets

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.poly.herewego.ui.theme.AppTypography

@Composable
fun Title(title: String) {
    Log.d("Title", "Recomposing Title")
    Text(title, style = AppTypography.headlineLarge, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 12.dp))
}