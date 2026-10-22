package com.eygraber.materialcolor

import com.eygraber.materialcolor.colorspaces.LchColor
import com.eygraber.materialcolor.colorspaces.RgbColor
import com.eygraber.materialcolor.util.argb
import com.eygraber.materialcolor.util.argbHex
import com.eygraber.materialcolor.util.createPalette
import com.eygraber.materialcolor.util.goldenPalettes
import com.eygraber.materialcolor.util.rgb
import com.eygraber.materialcolor.util.rgbHex
import com.eygraber.materialcolor.util.rgba
import com.eygraber.materialcolor.util.rgbaHex
import com.eygraber.materialcolor.util.toLabColor
import com.eygraber.materialcolor.util.toLchColor
import com.eygraber.materialcolor.util.toRgbColor
import com.eygraber.materialcolor.util.toRgbColorOrNull
import com.eygraber.materialcolor.util.toXyzColor
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class ConversionTest {
  @Test
  fun parsesSixAndEightDigitHex() {
    assertEquals(RgbColor(red = 1.0, green = 0.0, blue = 51 / 255.0), "FF0033".toRgbColorOrNull())
    assertEquals(
      RgbColor(red = 1.0, green = 0.0, blue = 51 / 255.0, alpha = 0x80 / 255.0),
      "FF003380".toRgbColorOrNull(),
    )
  }

  @Test
  fun rejectsInvalidHex() {
    assertNull("FF00".toRgbColorOrNull())
    assertNull("GG0033".toRgbColorOrNull())
  }

  @Test
  fun formatsHexAndPackedChannels() {
    val color = "0A1B2C80".toRgbColorOrNull()!!

    assertEquals("0A1B2C", color.rgbHex)
    assertEquals("0A1B2C80", color.rgbaHex)
    assertEquals("800A1B2C", color.argbHex)
    assertEquals(0x0A1B2Cu, color.rgb)
    assertEquals(0x0A1B2C80u, color.rgba)
    assertEquals(0x800A1B2Cu, color.argb)
  }

  @Test
  fun roundTripsThroughLch() {
    val color = "3F51B5".toRgbColorOrNull()!!

    val roundTripped = color
      .toXyzColor()
      .toLabColor()
      .toLchColor()
      .toLabColor()
      .toXyzColor()
      .toRgbColor()

    assertEquals(color.rgbHex, roundTripped.rgbHex)
  }

  @Test
  fun customPaletteKeepsBaseColorAtAnchor() {
    val base = "3F51B5".toRgbColorOrNull()!!.toXyzColor().toLabColor().toLchColor()

    val palette = base.createPalette()

    assertEquals(10, palette.size)
    assertEquals(5, palette.anchorIndex)
    assertEquals(base, palette[palette.anchorIndex])
  }

  @Test
  fun customPaletteUsesExplicitGoldenPalette() {
    val base = LchColor(lightness = 50.0, chroma = 40.0, hue = 120.0)
    val goldenPalette = goldenPalettes.first()

    val palette = base.createPalette(goldenPalette)

    assertEquals(goldenPalette.getClosestColorIndex(base), palette.anchorIndex)
    assertEquals(base, palette[palette.anchorIndex])
  }
}
