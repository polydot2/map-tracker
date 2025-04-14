package com.poly.herewego.presentation

import android.os.Bundle
import androidx.navigation.NavType
import com.poly.herewego.data.Passport
import com.poly.herewego.data.Place
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal val PassportParameterType = object : NavType<Passport>(
    isNullableAllowed = false
) {
    override fun get(bundle: Bundle, key: String): Passport? =
        bundle.getString(key)?.let { parseValue(it) }

    override fun put(bundle: Bundle, key: String, value: Passport) {
        bundle.putString(key, serializeAsValue(value))
    }

    override fun parseValue(value: String): Passport = Json.decodeFromString(value)

    override fun serializeAsValue(value: Passport): String = Json.encodeToString(value)
}
