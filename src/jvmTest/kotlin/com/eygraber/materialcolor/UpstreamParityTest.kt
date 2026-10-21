package com.eygraber.materialcolor

import com.eygraber.materialcolor.colorspaces.RgbColor
import com.eygraber.materialcolor.util.createPalette
import com.eygraber.materialcolor.util.goldenPalettes
import com.eygraber.materialcolor.util.rgbHex
import com.eygraber.materialcolor.util.rgbaHex
import com.eygraber.materialcolor.util.toLabColor
import com.eygraber.materialcolor.util.toLchColor
import com.eygraber.materialcolor.util.toRgbColor
import com.eygraber.materialcolor.util.toXyzColor
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals
import de.hammwerk.material.color.colorspaces.LchColor as UpstreamLchColor
import de.hammwerk.material.color.colorspaces.RgbColor as UpstreamRgbColor
import de.hammwerk.material.color.util.createPalette as upstreamCreatePalette
import de.hammwerk.material.color.util.goldenPalettes as upstreamGoldenPalettes
import de.hammwerk.material.color.util.rgbHex as upstreamRgbHex
import de.hammwerk.material.color.util.rgbaHex as upstreamRgbaHex
import de.hammwerk.material.color.util.toLabColor as upstreamToLabColor
import de.hammwerk.material.color.util.toLchColor as upstreamToLchColor
import de.hammwerk.material.color.util.toXyzColor as upstreamToXyzColor

class UpstreamParityTest {
  private val random = Random(seed = 520)

  private val colors = List(SAMPLE_SIZE) {
    RgbColor(random.nextInt(256) / 255.0, random.nextInt(256) / 255.0, random.nextInt(256) / 255.0)
  } + goldenPalettes.flatten().map { it.toLabColor().toXyzColor().toRgbColor() }

  @Test
  fun goldenPalettesMatch() {
    assertEquals(
      upstreamGoldenPalettes.map { palette -> palette.map { listOf(it.lightness, it.chroma, it.hue) } },
      goldenPalettes.map { palette -> palette.map { listOf(it.lightness, it.chroma, it.hue) } },
    )
  }

  @Test
  fun rgbPalettesMatch() {
    colors.forEach { color ->
      assertEquals(
        color.toUpstream().upstreamCreatePalette().map { it.upstreamRgbaHex },
        color.createPalette().map { it.rgbaHex },
        "palette for ${color.rgbHex}",
      )
    }
  }

  @Test
  fun lchPalettesMatch() {
    colors.forEach { color ->
      val lch = color.toXyzColor().toLabColor().toLchColor()
      val upstreamLch = color.toUpstream().upstreamToXyzColor().upstreamToLabColor().upstreamToLchColor()

      assertEquals(
        upstreamLch.upstreamCreatePalette().map { it.toList() },
        lch.createPalette().map { listOf(it.lightness, it.chroma, it.hue, it.alpha) },
        "palette for ${color.rgbHex}",
      )
    }
  }

  @Test
  fun deltaEMatches() {
    colors.zipWithNext().forEach { (first, second) ->
      val upstreamFirst = first.toUpstream().upstreamToXyzColor().upstreamToLabColor().upstreamToLchColor()
      val upstreamSecond = second.toUpstream().upstreamToXyzColor().upstreamToLabColor().upstreamToLchColor()

      assertEquals(
        upstreamFirst.deltaE(upstreamSecond),
        first.toXyzColor().toLabColor().toLchColor().deltaE(second.toXyzColor().toLabColor().toLchColor()),
      )
    }
  }

  @Test
  fun hexMatches() {
    colors.forEach { color ->
      assertEquals(color.toUpstream().upstreamRgbHex, color.rgbHex)
    }
  }

  private fun RgbColor.toUpstream() = UpstreamRgbColor(red, green, blue, alpha)

  private fun UpstreamLchColor.toList() = listOf(lightness, chroma, hue, alpha)

  private companion object {
    const val SAMPLE_SIZE = 2_000
  }
}
