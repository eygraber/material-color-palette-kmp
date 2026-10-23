# Material Color Palette (Kotlin Multiplatform)

This library creates a Material Design color palette of 10 stops from one base color. It is a Kotlin Multiplatform port of [de.hammwerk:material-color-palette] by David Hamm. That library is a reverse-engineered version of the palette tool from the Material Design 2 guidelines.

## Setup

```kotlin
repositories {
  mavenCentral()
}

dependencies {
  implementation("com.eygraber:material-color-palette-kmp:1.0.1")
}
```

Snapshots are in the [Central Portal snapshots repository].

## Usage

### Create a palette from a hex color

```kotlin
val palette: List<RgbColor> = "F44336".toRgbColorOrNull()!!.createPalette()
palette.map { it.rgbHex }
```

The result is the Material Design red palette:

```kotlin
listOf("FFEBEE", "FFCDD2", "EF9A9A", "E57373", "EF5350", "F44336", "E53935", "D32F2F", "C62828", "B71B1C")
```

`toRgbColorOrNull` accepts 6 hex digits (`RRGGBB`) or 8 hex digits (`RRGGBBAA`) without a `#` prefix. For other input, it returns `null`.

### Read the output

`RgbColor` holds each channel as a `Double` from 0 to 1. Use these properties to get other formats:

| Property  | Type     | Format       |
|-----------|----------|--------------|
| `rgbHex`  | `String` | `RRGGBB`     |
| `rgbaHex` | `String` | `RRGGBBAA`   |
| `argbHex` | `String` | `AARRGGBB`   |
| `rgb`     | `UInt`   | `0xRRGGBB`   |
| `rgba`    | `UInt`   | `0xRRGGBBAA` |
| `argb`    | `UInt`   | `0xAARRGGBB` |

### Create a palette in the LCH color space

`LchColor.createPalette()` returns a `CustomPalette`, which is a `List<LchColor>`. The `anchorIndex` property is the index of the stop that holds the base color.

```kotlin
val base: LchColor = "F44336".toRgbColorOrNull()!!.toXyzColor().toLabColor().toLchColor()
val palette: CustomPalette = base.createPalette()
palette[palette.anchorIndex] == base
```

### Select a golden palette

The library creates each palette from one of the Material Design golden palettes. By default, it uses the golden palette that is closest to the base color. To use a different golden palette, pass it to `createPalette`:

```kotlin
val goldenPalette = goldenPalettes.first()
base.createPalette(goldenPalette)
```

`LchColor.closestGoldenPalette()` returns the default golden palette for a color.

### Convert between color spaces

The library supports these conversions:

- `RgbColor` to `HslColor` or `XyzColor`
- `HslColor` to `RgbColor`
- `XyzColor` to `RgbColor` or `LabColor`
- `LabColor` to `XyzColor` or `LchColor`
- `LchColor` to `LabColor`

## Migrate from de.hammwerk:material-color-palette

The algorithm and the golden palette data are the same as in upstream version 1.0.0. A test checks that the output matches the upstream output for more than 2,000 colors.

The API changes are:

- The package is `com.eygraber.materialcolor`. Upstream uses `de.hammwerk.material.color`.
- `LchColor.createPalette()` returns a `CustomPalette` that holds the anchor index. Upstream returns a `List<LchColor>`.
- `LchColor.createPalette()` accepts an optional `GoldenPalette`.
- `LchColor.closestGoldenPalette()` and `GoldenPalette.getClosestColorIndex()` are new.
- `Double.toRadians()`, `Double.toDegrees()`, and `Double.roundToUInt()` are internal.

## License

MIT. Refer to [LICENSE]. The original work is copyright (c) 2021 David Hamm.

[Central Portal snapshots repository]: https://central.sonatype.org/publish/publish-portal-snapshots/#consuming-via-gradle
[de.hammwerk:material-color-palette]: https://github.com/Hammwerk/material-color-palette
[LICENSE]: /LICENSE
