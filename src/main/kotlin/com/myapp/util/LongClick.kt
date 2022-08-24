package com.myapp.util

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LongPressButton(
  modifier: Modifier = Modifier,
  onClick: () -> Unit = {},
  onLongPress: () -> Unit = {},
  //onDoubleClick: () -> Unit = {},
  enabled: Boolean = true,
  interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
  elevation: ButtonElevation? = ButtonDefaults.elevation(),
  shape: Shape = MaterialTheme.shapes.small,
  border: BorderStroke? = null,
  colors: ButtonColors = ButtonDefaults.buttonColors(),
  contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
  content: @Composable RowScope.() -> Unit
) {
  val contentColor by colors.contentColor(enabled)
  Surface(
    modifier = modifier,
    shape = shape,
    color = colors.backgroundColor(enabled).value,
    contentColor = contentColor.copy(alpha = 1f),
    border = border,
    elevation = elevation?.elevation(enabled, interactionSource)?.value ?: 0.dp,
    clickAndSemanticsModifier = Modifier.combinedClickable(
      interactionSource = interactionSource,
      indication = rememberRipple(),
      enabled = enabled,
      role = Role.Button,
      onClick = onClick,
      //onDoubleClick = onDoubleClick,
      onLongClick = onLongPress,
    )
  ) {
    CompositionLocalProvider(LocalContentAlpha provides contentColor.alpha) {
      ProvideTextStyle(
        value = MaterialTheme.typography.button
      ) {
        Row(
          Modifier
            .defaultMinSize(
              minWidth = ButtonDefaults.MinWidth,
              minHeight = ButtonDefaults.MinHeight
            )
            .padding(contentPadding),
          horizontalArrangement = Arrangement.Center,
          verticalAlignment = Alignment.CenterVertically,
          content = content
        )
      }
    }
  }
}


@Composable
private fun Surface(
  modifier: Modifier,
  shape: Shape,
  color: Color,
  contentColor: Color,
  border: BorderStroke?,
  elevation: Dp,
  clickAndSemanticsModifier: Modifier,
  content: @Composable () -> Unit
) {
  val elevationOverlay = LocalElevationOverlay.current
  val absoluteElevation = LocalAbsoluteElevation.current + elevation
  val backgroundColor = if (color == MaterialTheme.colors.surface && elevationOverlay != null) {
    elevationOverlay.apply(color, absoluteElevation)
  } else {
    color
  }
  CompositionLocalProvider(
    LocalContentColor provides contentColor,
    LocalAbsoluteElevation provides absoluteElevation
  ) {
    Box(
      modifier
        .shadow(elevation, shape, clip = false)
        .then(if (border != null) Modifier.border(border, shape) else Modifier)
        .background(
          color = backgroundColor,
          shape = shape
        )
        .clip(shape)
        .then(clickAndSemanticsModifier),
      propagateMinConstraints = true
    ) {
      content()
    }
  }
}