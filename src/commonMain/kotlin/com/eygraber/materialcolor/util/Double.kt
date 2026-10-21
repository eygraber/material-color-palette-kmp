package com.eygraber.materialcolor.util

import kotlin.math.PI
import kotlin.math.roundToInt

internal fun Double.toRadians(): Double = this * PI / 180.0

internal fun Double.toDegrees(): Double = this * 180.0 / PI

internal fun Double.roundToUInt(): UInt = roundToInt().toUInt()
