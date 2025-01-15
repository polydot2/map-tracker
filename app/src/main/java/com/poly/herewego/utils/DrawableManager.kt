package com.poly.herewego.utils

import com.poly.herewego.R
import java.util.Locale

object DrawableManager {
    fun getCategoryIcon(name: String): Int {
        return when (name.lowercase(Locale.getDefault())) {
            "paris" -> R.drawable.paris
            "nantes" -> R.drawable.holland
            "rome" -> R.drawable.rome
            else -> R.drawable.greece
        }
    }
}