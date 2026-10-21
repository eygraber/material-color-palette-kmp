package com.eygraber.materialcolor

import com.eygraber.materialcolor.colorspaces.LchColor

public data class GoldenPalette(private val colors: List<LchColor>) : List<LchColor> by colors {
  public fun minDeltaE(color: LchColor): Double = minOf { color.deltaE(it) }

  public fun getClosestColor(color: LchColor): LchColor = minBy { color.deltaE(it) }

  public fun getClosestColorIndex(color: LchColor): Int = indexOf(getClosestColor(color))

  public fun hasMainColorLowChroma(): Boolean = this[5].chroma < 30
}
