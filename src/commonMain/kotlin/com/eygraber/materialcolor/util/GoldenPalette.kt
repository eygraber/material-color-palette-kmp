package com.eygraber.materialcolor.util

import com.eygraber.materialcolor.CustomPalette
import com.eygraber.materialcolor.GoldenPalette
import com.eygraber.materialcolor.colorspaces.LchColor

public fun GoldenPalette.createCustomPalette(customBaseColor: LchColor): CustomPalette {
  var maxLightness = 100.0

  val closestGoldenPaletteColor = getClosestColor(customBaseColor)
  val closestColorIndex = indexOf(closestGoldenPaletteColor)

  val colors = mapIndexed { index, goldenPaletteColor ->
    when (index) {
      closestColorIndex -> customBaseColor

      else -> (
        goldenPaletteColor - (closestGoldenPaletteColor - customBaseColor)
          .adjustLightness { it * (lightnessFactors[index] / lightnessFactors[closestColorIndex]) }
          .adjustChroma { chroma ->
            chroma * when {
              hasMainColorLowChroma() -> 1.0
              else -> (chromaFactors[index] / chromaFactors[closestColorIndex]).coerceAtMost(1.25)
            }
          }
        )
        .adjustLightness { it.coerceIn(0.0, maxLightness) }
        .adjustChroma { it.coerceAtLeast(0.0) }
        .adjustHue { (it + 360.0) % 360 }
    }.also { maxLightness = (it.lightness - 1.7).coerceAtLeast(0.0) }
  }
  return CustomPalette(colors = colors, anchorIndex = closestColorIndex)
}

private val lightnessFactors = doubleArrayOf(
  2.048875457,
  5.124792061,
  8.751659557,
  12.07628774,
  13.91449542,
  15.92738893,
  15.46585818,
  15.09779227,
  15.13738673,
  15.09818372,
)
private val chromaFactors = doubleArrayOf(
  1.762442714,
  4.213532634,
  7.395827458,
  11.07174158,
  13.89634504,
  16.37591477,
  16.27071136,
  16.54160806,
  17.35916727,
  19.88410864,
)
