package ru.involta.actify.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
  primary = Color(0xffE2093B),
  primaryVariant = Color(0xFFFF5179),
  secondary = Color(0xFFF57C00),
  secondaryVariant = Color(0xFFE64A19)
)

private val LightColorPalette = lightColors(
  primary = Color(0xffE2093B),
  primaryVariant = Color(0xFFFF5179),
  secondary = Color(0xFFF57C00),
  secondaryVariant = Color(0xFFE64A19)

  /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun shimmerBgColor(): Color = if (isSystemInDarkTheme())
  Color(0x1AFFFFFF) else
  Color(0x1A000000)

@Composable
fun ActifyTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
  val colors = if (darkTheme) {
    DarkColorPalette
  } else {
    LightColorPalette
  }

  MaterialTheme(
    colors = colors,
    typography = Typography,
    shapes = Shapes,
    content = content
  )
}