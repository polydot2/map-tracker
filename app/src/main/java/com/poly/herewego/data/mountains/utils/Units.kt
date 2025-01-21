package com.poly.herewego.data.mountains.utils

import android.content.res.Resources
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.res.stringResource
import com.poly.herewego.R

const val METERS_PER_FOOT = 3.28084
const val METERS_PER_KILOMETER = 1_000
const val FEET_PER_METER = 1 / METERS_PER_FOOT
const val FEET_PER_MILE = 5_280
const val MILES_PER_METER = 0.000621371

/**
 * A value class to wrap a value representing a measurement in meters.
 */
@Immutable
@JvmInline
value class Meters(val value: Double) : Comparable<Meters> {
    override fun compareTo(other: Meters) = value.compareTo(other.value)

    operator fun minus(other: Meters) = Meters(value = this.value - other.value)
}

/**
 * Create a Meters class from a [Number]
 */
@Stable
inline val Number.meters: Meters get() = Meters(value = this.toDouble())

/**
 * Create a Meters class from a [Number]
 */
@Stable
inline val Number.m: Meters get() = Meters(value = this.toDouble())

/**
 * Create a Meters class from a [Number] of kilometers
 */
@Stable
inline val Number.km: Meters get() = Meters(value = this.toDouble() * METERS_PER_KILOMETER)

/**
 * Create a Meters class from a [Number] of feet
 */
@Stable
inline val Number.feet: Meters get() = Meters(value = this.toDouble() * FEET_PER_METER)

/**
 * Create a Meters class from a [Number] of miles
 */
@Stable
inline val Number.miles: Meters get() = Meters(value = this.toDouble() / MILES_PER_METER)

/**
 * Gets the number of equivalent feet from a meters value class
 */
@Stable
inline val Meters.toFeet: Double get() = value * METERS_PER_FOOT

/**
 * Gets the value of a meters class as a Double
 */
@Stable
inline val Meters.toMeters: Double get() = value

/**
 * Gets the number of equivalent kilometers from a meters value class
 */
@Stable
inline val Meters.toKilometers: Double get() = value / METERS_PER_KILOMETER

/**
 * Gets the number of equivalent kilometers from a meters value class
 */
@Stable
inline val Meters.toMiles: Double get() = (value * MILES_PER_METER)

data class ValueWithUnitsTemplate(
    val value: Double,
    @StringRes val unitsTemplate: Int
)

abstract class UnitsConverter {
    abstract fun toElevationUnits(meters: Meters): ValueWithUnitsTemplate
    abstract fun toDistanceUnits(meters: Meters): ValueWithUnitsTemplate

    @Composable
    fun toElevationString(meters: Meters): String {
        val (value, resourceId) = toElevationUnits(meters = meters)
        return stringResource(id = resourceId, value)
    }

    fun toElevationString(resources: Resources, meters: Meters): String {
        val (value, resourceId) = toElevationUnits(meters = meters)
        return resources.getString(resourceId, value)
    }

    @Composable
    fun toDistanceString(meters: Meters): String {
        val (value, resourceId) = toDistanceUnits(meters = meters)
        return stringResource(id = resourceId, value)
    }

    fun toDistanceString(resources: Resources, meters: Meters): String {
        val (value, resourceId) = toDistanceUnits(meters = meters)
        return resources.getString(resourceId, value)
    }
}

object ImperialUnitsConverter : UnitsConverter() {
    override fun toElevationUnits(meters: Meters) =
        ValueWithUnitsTemplate(meters.toFeet, R.string.in_feet)

    override fun toDistanceUnits(meters: Meters) =
        ValueWithUnitsTemplate(meters.toMiles, R.string.in_miles)
}

object MetricUnitsConverter : UnitsConverter() {
    override fun toElevationUnits(meters: Meters) =
        ValueWithUnitsTemplate(meters.toMeters, R.string.in_meters)

    override fun toDistanceUnits(meters: Meters) =
        ValueWithUnitsTemplate(meters.toKilometers, R.string.in_kilometers)
}

val LocalUnitsConverter = compositionLocalOf<UnitsConverter> { ImperialUnitsConverter }

/**
 * Creates a string to show the elevation formatted with units
 */
@Composable
fun Meters.toElevationString(): String {
    return LocalUnitsConverter.current.toElevationString(this)
}