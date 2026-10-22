package com.eygraber.materialcolor.util

import com.eygraber.materialcolor.colorspaces.RgbColor

public fun String.toRgbColorOrNull(): RgbColor? = toUIntOrNull(16)?.let { value ->
  when(length) {
    8 -> RgbColor(
      red = (value shr 24 and 0xffu).toInt() / 255.0,
      green = (value shr 16 and 0xffu).toInt() / 255.0,
      blue = (value shr 8 and 0xffu).toInt() / 255.0,
      alpha = (value and 0x000000ffu).toInt() / 255.0,
    )

    6 -> RgbColor(
      red = (value shr 16 and 0xffu).toInt() / 255.0,
      green = (value shr 8 and 0xffu).toInt() / 255.0,
      blue = (value and 0xffu).toInt() / 255.0,
    )

    else -> null
  }
}
