package com.myapp.ui.element

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp

@Composable
fun ActifyButton(
  modifier: Modifier = Modifier,
  icon: @Composable () -> Unit = {},
  enabled: Boolean = true,
  isLoading: Boolean = false,
  onClick: () -> Unit,
  content: @Composable () -> Unit
) {
  val haptic = LocalHapticFeedback.current
  SmoothEnabledButton(
    modifier = modifier.fillMaxWidth(),
    onClick = {
      haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
      onClick.invoke()
    },
    enabled = enabled,
    isLoading = isLoading,
  ) {
    Box(Modifier.fillMaxWidth()) {
      Box(Modifier.align(Alignment.CenterStart)) {
        icon.invoke()
      }
      Box(Modifier.align(Alignment.Center)) {
        content.invoke()
      }
    }
  }
}

@Composable
fun ActifyButton(
  modifier: Modifier = Modifier,
  icon: ImageVector,
  enabled: Boolean = true,
  isLoading: Boolean = false,
  onClick: () -> Unit,
  content: @Composable () -> Unit
) = ActifyButton(modifier, @Composable { Icon(icon, "") }, enabled, isLoading, onClick, content)


@Composable
fun ActifyButton(
  text: String,
  modifier: Modifier = Modifier,
  icon: @Composable () -> Unit = {},
  enabled: Boolean = true,
  isLoading: Boolean = false,
  onClick: () -> Unit
) = ActifyButton(modifier, icon, enabled, isLoading, onClick) { Text(text = text) }


@Composable
fun ActifyButton(
  text: String,
  icon: ImageVector,
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  isLoading: Boolean = false,
  onClick: () -> Unit
) = ActifyButton(text, modifier, @Composable { Icon(icon, "") }, enabled, isLoading, onClick)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SmoothEnabledButton(
  onClick: () -> Unit,
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
  elevation: ButtonElevation? = ButtonDefaults.elevation(),
  shape: Shape = RoundedCornerShape(8.dp),
  border: BorderStroke? = null,
  colors: ButtonColors = ButtonDefaults.buttonColors(),
  contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
  isLoading: Boolean = false,
  content: @Composable RowScope.() -> Unit,
) {
  val contentColor by colors.contentColor(enabled)
  val color =
    animateColorAsState(
      targetValue = if (enabled) colors.backgroundColor(true).value else colors.backgroundColor(false).value,
      animationSpec = tween(700)
    )
  Surface(
    onClick = onClick,
    modifier = modifier,
    enabled = enabled,
    shape = shape,
    color = color.value,
    contentColor = contentColor.copy(alpha = 1f),
    border = border,
    elevation = elevation?.elevation(enabled, interactionSource)?.value ?: 0.dp,
    interactionSource = interactionSource,
  ) {
    CompositionLocalProvider(LocalContentAlpha provides contentColor.alpha) {
      ProvideTextStyle(
        value = MaterialTheme.typography.button
      ) {
        Column {
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
          AnimatedVisibility(visible = isLoading) {
            LinearProgressIndicator(Modifier.fillMaxWidth())
          }
        }
      }
    }
  }
}