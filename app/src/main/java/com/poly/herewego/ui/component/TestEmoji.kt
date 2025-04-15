package com.poly.herewego.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.poly.herewego.R
import com.poly.herewego.ui.theme.AppTheme

@Composable
fun EmojiWithCustomFont() {
    val emojiFont = FontFamily(Font(R.font.twemoji))

    Text(
        text = "Hello 😊👍",
        fontFamily = emojiFont,
        fontSize = 24.sp
    )
}

@Preview
@Composable
fun Emoji() {
    AppTheme {
        EmojiWithCustomFont()
    }
}
