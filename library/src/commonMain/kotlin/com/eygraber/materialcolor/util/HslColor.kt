package com.eygraber.materialcolor.util

import com.eygraber.materialcolor.colorspaces.HslColor
import com.eygraber.materialcolor.colorspaces.RgbColor
import kotlin.math.absoluteValue

public fun HslColor.toRgbColor(): RgbColor {
  val chroma = (1 - (2.0 * lightness - 1.0).absoluteValue) * saturation
  val huePrime = hue / 60.0
  val x = chroma * (1 - (huePrime % 2 - 1).absoluteValue)
  val m = lightness - chroma / 2.0

  return when {
    huePrime < 1.0 -> RgbColor(red = chroma + m, green = x + m, blue = m, alpha = alpha)
    huePrime < 2.0 -> RgbColor(red = x + m, green = chroma + m, blue = m, alpha = alpha)
    huePrime < 3.0 -> RgbColor(red = m, green = chroma + m, blue = x + m, alpha = alpha)
    huePrime < 4.0 -> RgbColor(red = m, green = x + m, blue = chroma + m, alpha = alpha)
    huePrime < 5.0 -> RgbColor(red = x + m, green = m, blue = chroma + m, alpha = alpha)
    else -> RgbColor(red = chroma + m, green = m, blue = x + m, alpha = alpha)
  }
}
