package com.eygraber.materialcolor

import com.eygraber.materialcolor.colorspaces.LchColor

public data class CustomPalette(
  private val colors: List<LchColor>,
  val anchorIndex: Int,
) : List<LchColor> by colors
