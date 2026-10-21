# Material Color Palette (Kotlin Multiplatform)

This library generates a Material Design color palette of 10 stops from one base color. It is a Kotlin Multiplatform port of [de.hammwerk:material-color-palette] by David Hamm. That library is a reverse-engineered version of the palette tool from Google's Material Design 2 guidelines.

## Usage

```kotlin
val palette: List<RgbColor> = "F44336".toRgbColorOrNull()!!.createPalette()
palette.map { it.rgbHex }
```

The result is the Material Design red palette:

```kotlin
listOf("FFEBEE", "FFCDD2", "EF9A9A", "E57373", "EF5350", "F44336", "E53935", "D32F2F", "C62828", "B71B1C")
```

To keep the color space, use the LCH API. It returns a `CustomPalette`, which is a `List<LchColor>`. Its `anchorIndex` property is the index of the stop that holds the base color.

```kotlin
val base: LchColor = "F44336".toRgbColorOrNull()!!.toXyzColor().toLabColor().toLchColor()
val palette: CustomPalette = base.createPalette()
palette[palette.anchorIndex] == base
```

To use a golden palette other than the closest one, pass it to `createPalette`:

```kotlin
val goldenPalette = goldenPalettes.first()
base.createPalette(goldenPalette)
```

## Gradle

```kotlin
dependencies {
  implementation("com.eygraber:material-color-palette-kmp:1.0.0")
}
```

## Differences from the upstream library

The algorithm and the golden palette data are the same as in upstream version 1.0.0. On the JVM, a test compares the output with the upstream artifact for more than 2,000 colors. The match is exact.

The API changes are:

- The package is `com.eygraber.materialcolor`. Upstream uses `de.hammwerk.material.color`.
- `LchColor.createPalette()` returns a `CustomPalette` that holds the anchor index. Upstream returns a `List<LchColor>`.
- `LchColor.createPalette()` accepts an optional `GoldenPalette`.
- `LchColor.closestGoldenPalette()` and `GoldenPalette.getClosestColorIndex()` are new.
- `Double.toRadians()`, `Double.toDegrees()`, and `Double.roundToUInt()` are internal.

The JVM-only calls `String.format` and `Math.PI` are replaced with common code.

## Build

```shell
./gradlew allTests
./gradlew publishToMavenLocal
```

To publish to Maven Central, set the GPG key and the Central Portal credentials. The [Gradle Maven Publish Plugin] documentation lists the properties.

## License

MIT. Refer to [LICENSE]. The original work is copyright (c) 2021 David Hamm.

[de.hammwerk:material-color-palette]: https://github.com/Hammwerk/material-color-palette
[Gradle Maven Publish Plugin]: https://vanniktech.github.io/gradle-maven-publish-plugin/central/
[LICENSE]: LICENSE
