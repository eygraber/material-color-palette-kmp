package com.eygraber.materialcolor.util

import com.eygraber.materialcolor.colorspaces.HslColor
import com.eygraber.materialcolor.colorspaces.RgbColor
import com.eygraber.materialcolor.colorspaces.XyzColor
import kotlin.math.absoluteValue
import kotlin.math.pow
import kotlin.math.roundToInt

public fun RgbColor.toHslColor(): HslColor {
  val max = maxOf(red, green, blue)
  val min = minOf(red, green, blue)
  val delta = max - min
  return HslColor(
    hue = ((rawHue(max = max, delta = delta) + 360.0) % 360.0).roundToInt(),
    saturation = when {
      max == 0.0 || min == 1.0 -> 0.0
      else -> delta / (1 - (max + min - 1).absoluteValue)
    },
    lightness = (max + min) / 2.0,
    alpha = alpha,
  )
}

private fun RgbColor.rawHue(max: Double, delta: Double): Double = when {
  delta == 0.0 -> 0.0
  max == red -> 60.0 * ((green - blue) / delta)
  max == green -> 60.0 * ((blue - red) / delta + 2)
  else -> 60.0 * ((red - green) / delta + 4)
}

public fun RgbColor.toXyzColor(): XyzColor = XyzColor(
  x = .4124564 * red.correctGamma() + .3575761 * green.correctGamma() + .1804375 * blue.correctGamma(),
  y = .2126729 * red.correctGamma() + .7151522 * green.correctGamma() + .0721750 * blue.correctGamma(),
  z = .0193339 * red.correctGamma() + .1191920 * green.correctGamma() + .9503041 * blue.correctGamma(),
  alpha = alpha,
)

public fun RgbColor.createPalette(): List<RgbColor> = toHslColor()
  .toRgbColor()
  .toXyzColor()
  .toLabColor()
  .toLchColor()
  .createPalette()
  .map { color ->
    color
      .toLabColor()
      .toXyzColor()
      .toRgbColor()
  }

public val RgbColor.rgbHex: String
  get() = hex(red, green, blue)

public val RgbColor.rgbaHex: String
  get() = hex(red, green, blue, alpha)

public val RgbColor.argbHex: String
  get() = hex(alpha, red, green, blue)

public val RgbColor.rgb: UInt
  get() = (red * 0xff).roundToUInt() shl 16 and 0x00ff0000u or
    ((green * 0xff).roundToUInt() shl 8 and 0x0000ff00u) or
    ((blue * 0xff).roundToUInt() and 0x000000ffu)

public val RgbColor.rgba: UInt
  get() = (red * 0xff).roundToUInt() shl 24 and 0xff000000u or
    ((green * 0xff).roundToUInt() shl 16 and 0x00ff0000u) or
    ((blue * 0xff).roundToUInt() shl 8 and 0x0000ff00u) or
    ((alpha * 0xff).roundToUInt() and 0x000000ffu)

public val RgbColor.argb: UInt
  get() = (alpha * 0xff).roundToUInt() shl 24 and 0xff000000u or
    ((red * 0xff).roundToUInt() shl 16 and 0x00ff0000u) or
    ((green * 0xff).roundToUInt() shl 8 and 0x0000ff00u) or
    ((blue * 0xff).roundToUInt() and 0x000000ffu)

private fun hex(vararg channels: Double): String = channels.joinToString(separator = "") {
  (it * 0xff).roundToInt().toString(16).uppercase().padStart(2, '0')
}

private fun Double.correctGamma(): Double = if(this <= .04045) this / 12.92 else ((this + .055) / 1.055).pow(2.4)
