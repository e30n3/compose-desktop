package ru.involta.actify.ui.element

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import ru.involta.actify.util.extention.clearFocusOnKeyboardDismiss


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ActifyTextField(
  value: String,
  onValueChange: (String) -> Unit,
  modifier: Modifier = Modifier,
  label: String = "",
  keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
  enabled: Boolean = true,
  isError: Boolean = false,
  visualTransformation: VisualTransformation = VisualTransformation.None,
  onStart: () -> Unit = {},
  onDone: () -> Unit = {},
  onFocus: () -> Unit = {},
  trailingIcon: @Composable (() -> Unit)? = null,
) {
  val keyboard = LocalSoftwareKeyboardController.current
  TextField(
    value = value,
    onValueChange = onValueChange,
    modifier
      .fillMaxWidth()
      .clearFocusOnKeyboardDismiss()
      .onFocusChanged {
        onFocus.invoke()
        if (it.isFocused) {
          // focused
          if (value.isNotBlank()) onStart.invoke()
        } else {
          // not focused
          if (value.isNotBlank()) onDone.invoke()
        }
      },
    label = { Text(text = label) },
    maxLines = 1,
    singleLine = true,
    keyboardOptions = keyboardOptions,
    keyboardActions = KeyboardActions(onDone = {
      keyboard?.hide()
    }),
    enabled = enabled,
    isError = isError,
    trailingIcon = trailingIcon,
    shape = RoundedCornerShape(8.dp, 8.dp),
    visualTransformation = visualTransformation
  )
}