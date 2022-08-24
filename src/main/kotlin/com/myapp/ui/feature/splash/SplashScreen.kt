package com.myapp.ui.feature.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.myapp.ui.value.R

@Composable
fun SplashScreen(
    viewModel: SplashViewModel,
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.surface,
        elevation = 16.dp
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(Modifier.weight(1f))
            Icon(
                painter = painterResource(R.drawable.actifyLogo),
                modifier = Modifier.fillMaxSize().weight(1f),
                contentDescription = "Logo",
                tint = MaterialTheme.colors.primary
            )
            Spacer(Modifier.weight(1f))
        }
    }
}