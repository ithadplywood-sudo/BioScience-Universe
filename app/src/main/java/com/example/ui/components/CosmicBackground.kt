package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.ui.theme.CyanPrimary
import com.example.ui.theme.DeepCobalt
import com.example.ui.theme.DeepSpaceDark
import com.example.ui.theme.ElectricBlue
import com.example.ui.theme.SpaceSurface
import kotlin.random.Random

@Composable
fun CosmicBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    // Generate a fixed set of star/particle coordinates
    val particles = remember {
        val rand = Random(42)
        List(40) {
            Triple(rand.nextFloat(), rand.nextFloat(), rand.nextFloat() * 2.2f + 0.8f)
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        DeepSpaceDark,
                        SpaceSurface,
                        DeepSpaceDark
                    )
                )
            )
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height

            // Subtle cosmic aura in upper right
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(DeepCobalt.copy(alpha = 0.25f), Color.Transparent),
                    center = Offset(width * 0.85f, height * 0.15f),
                    radius = width * 0.7f
                )
            )

            // Subtle cyan aura in bottom left
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(CyanPrimary.copy(alpha = 0.12f), Color.Transparent),
                    center = Offset(width * 0.15f, height * 0.85f),
                    radius = width * 0.6f
                )
            )

            // Draw cosmic stars / bio-nodes
            particles.forEach { (rx, ry, r) ->
                val px = rx * width
                val py = ry * height
                drawCircle(
                    color = if (r > 2f) CyanPrimary.copy(alpha = 0.6f) else ElectricBlue.copy(alpha = 0.35f),
                    radius = r,
                    center = Offset(px, py)
                )
            }
        }

        content()
    }
}
