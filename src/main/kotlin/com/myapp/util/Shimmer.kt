package com.myapp.util

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun ShimmerBrush(): Brush {
  val shimmerColors =
    if (isSystemInDarkTheme())
      listOf(
        Color.White.copy(alpha = 0.0f),
        Color.White.copy(alpha = 0.1f),
        Color.White.copy(alpha = 0.0f),
      ) else
      listOf(
        Color.Black.copy(alpha = 0.1f),
        Color.Black.copy(alpha = 0.0f),
        Color.Black.copy(alpha = 0.1f),
      )


  val transition = rememberInfiniteTransition()
  val translateAnim = transition.animateFloat(
    initialValue = 0f,
    targetValue = 1000f,
    animationSpec = infiniteRepeatable(
      animation = tween(
        durationMillis = 1000,
        easing = FastOutSlowInEasing
      ),
      repeatMode = RepeatMode.Reverse
    )
  )

  return Brush.linearGradient(
    colors = shimmerColors,
    start = Offset.Zero,
    end = Offset(x = translateAnim.value, y = translateAnim.value)
  )
}

@Composable
fun ShimmerGridItem(brush: Brush) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(all = 10.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Spacer(
      modifier = Modifier
        .size(80.dp)
        .clip(CircleShape)
        .background(brush)
    )
    Spacer(modifier = Modifier.width(10.dp))
    Column(verticalArrangement = Arrangement.Center) {
      Spacer(
        modifier = Modifier
          .height(20.dp)
          .clip(RoundedCornerShape(10.dp))
          .fillMaxWidth(fraction = 0.7f)
          .background(brush)
      )
      Spacer(modifier = Modifier.padding(5.dp))
      Spacer(
        modifier = Modifier
          .height(20.dp)
          .clip(RoundedCornerShape(10.dp))
          .fillMaxWidth(fraction = 0.9f)
          .background(brush)
      )
    }
  }
}