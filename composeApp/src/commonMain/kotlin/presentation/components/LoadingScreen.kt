package presentation.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp

@Composable
fun LoadingScreen() {
    val rotationAnimation by animateFloatAsState(
        targetValue = 360f,
        animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(28.dp)
                .rotate(rotationAnimation)
        )
    }
}