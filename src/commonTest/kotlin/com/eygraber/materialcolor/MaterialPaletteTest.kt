package com.eygraber.materialcolor

import com.eygraber.materialcolor.util.createPalette
import com.eygraber.materialcolor.util.rgbHex
import com.eygraber.materialcolor.util.toRgbColorOrNull
import kotlin.test.Test
import kotlin.test.assertEquals

class MaterialPaletteTest {
  @Test
  fun red() {
    assertPalette(
      base = "F44336",
      expected = listOf(
        "FFEBEE",
        "FFCDD2",
        "EF9A9A",
        "E57373",
        "EF5350",
        "F44336",
        "E53935",
        "D32F2F",
        "C62828",
        "B71B1C",
      ),
    )
  }

  @Test
  fun pink() {
    assertPalette(
      base = "E91E63",
      expected = listOf(
        "FCE4EC",
        "F8BBD0",
        "F48FB0",
        "F06291",
        "EC4079",
        "E91E62",
        "D81B5F",
        "C2185A",
        "AD1356",
        "880D4E",
      ),
    )
  }

  @Test
  fun purple() {
    assertPalette(
      base = "9C27B0",
      expected = listOf(
        "F3E5F5",
        "E1BEE7",
        "CE93D8",
        "BA68C8",
        "AB47BC",
        "9B27B0",
        "8D24AA",
        "7A1FA2",
        "691B9A",
        "49148C",
      ),
    )
  }

  @Test
  fun deepPurple() {
    assertPalette(
      base = "673AB7",
      expected = listOf(
        "EDE7F6",
        "D1C4E9",
        "B39DDB",
        "9675CD",
        "7F57C2",
        "683AB7",
        "5F35B1",
        "522DA8",
        "4627A0",
        "321B92",
      ),
    )
  }

  @Test
  fun indigo() {
    assertPalette(
      base = "3F51B5",
      expected = listOf(
        "E8EAF6",
        "C5CAE9",
        "9FA8DA",
        "7986CB",
        "5C6BC0",
        "3F51B5",
        "3949AB",
        "303F9F",
        "283593",
        "1A237E",
      ),
    )
  }

  @Test
  fun blue() {
    assertPalette(
      base = "2196F3",
      expected = listOf(
        "E3F2FD",
        "BBDEFB",
        "90C9F9",
        "63B4F6",
        "42A4F5",
        "2194F3",
        "1F87E5",
        "1A75D2",
        "1764C0",
        "1045A1",
      ),
    )
  }

  @Test
  fun lightBlue() {
    assertPalette(
      base = "03A9F4",
      expected = listOf(
        "E1F5FE",
        "B3E5FC",
        "81D3FA",
        "4EC2F7",
        "28B5F6",
        "03A8F4",
        "049AE5",
        "0487D1",
        "0476BD",
        "03569B",
      ),
    )
  }

  @Test
  fun cyan() {
    assertPalette(
      base = "00BCD4",
      expected = listOf(
        "E0F7FA",
        "B2EBF2",
        "80DEEA",
        "4DCFE1",
        "26C5DA",
        "00BBD4",
        "00ABC1",
        "0096A7",
        "00828F",
        "005F64",
      ),
    )
  }

  @Test
  fun teal() {
    assertPalette(
      base = "009688",
      expected = listOf(
        "E0F2F1",
        "B2DFDB",
        "80CBC3",
        "4DB6AB",
        "26A699",
        "009687",
        "00897A",
        "00796A",
        "00695B",
        "004D3F",
      ),
    )
  }

  @Test
  fun green() {
    assertPalette(
      base = "4CAF50",
      expected = listOf(
        "E8F5E9",
        "C8E6C9",
        "A5D6A7",
        "81C784",
        "66BB69",
        "4CAF4F",
        "43A046",
        "388E3B",
        "2E7D31",
        "1B5E1F",
      ),
    )
  }

  @Test
  fun lightGreen() {
    assertPalette(
      base = "8BC34A",
      expected = listOf(
        "F1F8E9",
        "DCEDC8",
        "C5E1A5",
        "AED581",
        "9CCC65",
        "8BC34A",
        "7CB342",
        "689F38",
        "558B2F",
        "33691E",
      ),
    )
  }

  @Test
  fun lime() {
    assertPalette(
      base = "CDDC39",
      expected = listOf(
        "F9FBE7",
        "F0F4C3",
        "E5EE9C",
        "DBE775",
        "D3E157",
        "CCDC39",
        "BFCA33",
        "AEB42B",
        "9D9D24",
        "817717",
      ),
    )
  }

  @Test
  fun yellow() {
    assertPalette(
      base = "FFEB3B",
      expected = listOf(
        "FFFDE7",
        "FFF9C4",
        "FFF59D",
        "FEF075",
        "FCEB55",
        "FFEB3B",
        "FDD835",
        "FBC02D",
        "F9A825",
        "F57F16",
      ),
    )
  }

  @Test
  fun amber() {
    assertPalette(
      base = "FFC107",
      expected = listOf(
        "FFF8E1",
        "FFECB3",
        "FFE082",
        "FFD54F",
        "FFCA28",
        "FFC107",
        "FFB300",
        "FFA000",
        "FF8F00",
        "FF6F00",
      ),
    )
  }

  @Test
  fun orange() {
    assertPalette(
      base = "FF9800",
      expected = listOf(
        "FFF3E0",
        "FFE0B2",
        "FFCD80",
        "FFB84D",
        "FFA826",
        "FF9900",
        "FB8D00",
        "F57D00",
        "EF6D00",
        "E65200",
      ),
    )
  }

  @Test
  fun deepOrange() {
    assertPalette(
      base = "FF5722",
      expected = listOf(
        "FBE9E7",
        "FFCCBC",
        "FFAB91",
        "FF8965",
        "FF6F43",
        "FF5622",
        "F4501E",
        "E64919",
        "D84215",
        "BF350C",
      ),
    )
  }

  @Test
  fun brown() {
    assertPalette(
      base = "795548",
      expected = listOf(
        "EFEBE9",
        "D7CCC8",
        "BCAAA4",
        "A1887F",
        "8D6E63",
        "795548",
        "6D4C41",
        "5D4037",
        "4E342E",
        "3E2723",
      ),
    )
  }

  @Test
  fun gray() {
    assertPalette(
      base = "9E9E9E",
      expected = listOf(
        "FAFAFA",
        "F5F5F5",
        "EEEEEE",
        "E0E0E0",
        "BDBDBD",
        "9E9E9E",
        "757575",
        "616161",
        "424242",
        "212121",
      ),
    )
  }

  @Test
  fun blueGray() {
    assertPalette(
      base = "607D8B",
      expected = listOf(
        "ECEFF1",
        "CFD8DC",
        "B0BEC5",
        "90A4AE",
        "78909C",
        "607D8B",
        "546E7A",
        "455A64",
        "37474F",
        "263238",
      ),
    )
  }

  private fun assertPalette(base: String, expected: List<String>) {
    assertEquals(expected, base.toRgbColorOrNull()!!.createPalette().map { it.rgbHex })
  }
}
