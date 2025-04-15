package com.poly.herewego.ui.utils

import androidx.compose.ui.graphics.Color

fun String.hexStringToColor(): Color {
    return Color(this.replace("0x", "", ignoreCase = true).toLong(16))
}

