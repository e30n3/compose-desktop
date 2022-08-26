package ru.involta.actify.util.extention


import androidx.compose.foundation.background
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.myapp.util.ShimmerBrush

/*
fun View.isKeyboardOpen(): Boolean {
  val rect = Rect()
  getWindowVisibleDisplayFrame(rect)
  val screenHeight = rootView.height
  val keypadHeight = screenHeight - rect.bottom
  return keypadHeight > screenHeight * 0.15
}
*/

/*
@Composable
fun rememberIsKeyboardOpen(): State<Boolean> {
  val view = LocalView.current
  return produceState(initialValue = view.isKeyboardOpen()) {
    val viewTreeObserver = view.viewTreeObserver
    val listener = ViewTreeObserver.OnGlobalLayoutListener { value = view.isKeyboardOpen() }
    viewTreeObserver.addOnGlobalLayoutListener(listener)

    awaitDispose { viewTreeObserver.removeOnGlobalLayoutListener(listener) }
  }
}
*/

fun Modifier.clearFocusOnKeyboardDismiss(): Modifier = composed {

  var isFocused by remember { mutableStateOf(false) }
  var keyboardAppearedSinceLastFocused by remember { mutableStateOf(false) }

  /*  if (isFocused) {
      val isKeyboardOpen by rememberIsKeyboardOpen()

      val focusManager = LocalFocusManager.current
      LaunchedEffect(isKeyboardOpen) {
        if (isKeyboardOpen) {
          keyboardAppearedSinceLastFocused = true
        } else if (keyboardAppearedSinceLastFocused) {
          focusManager.clearFocus()
        }
      }
    }*/
  onFocusEvent {
    if (isFocused != it.isFocused) {
      isFocused = it.isFocused
      if (isFocused) {
        keyboardAppearedSinceLastFocused = false
      }
    }
  }
}

@Composable
fun Modifier.shimmer(visibility: Boolean = true, shape: Shape = RectangleShape) =
  if (visibility) Modifier.background(ShimmerBrush(), shape) else Modifier

fun Modifier.glow(
  color: Color,
  alpha: Float = 0.2f,
  borderRadius: Dp = 0.dp,
  radius: Dp = 20.dp,
  offsetY: Dp = 0.dp,
  offsetX: Dp = 0.dp
) = this.drawBehind {
//    val transparentColor = android.graphics.Color.toArgb(color.copy(alpha = 0.0f).value.toLong())
//    val shadowColor = android.graphics.Color.toArgb(color.copy(alpha = alpha).value.toLong())
  val transparentColor = Color.Transparent.toArgb()
  val shadowColor = color.copy(alpha).toArgb()
  this.drawIntoCanvas {
    val paint = Paint()
    val frameworkPaint = paint.asFrameworkPaint()
    frameworkPaint.color = transparentColor
    /*frameworkPaint.setShadowLayer(
      radius.toPx(),
      offsetX.toPx(),
      offsetY.toPx(),
      shadowColor
    )*/
    it.drawRoundRect(
      0f,
      0f,
      this.size.width,
      this.size.height,
      borderRadius.toPx(),
      borderRadius.toPx(),
      paint
    )
  }
}
