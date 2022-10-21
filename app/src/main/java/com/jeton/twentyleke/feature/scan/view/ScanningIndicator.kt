package com.jeton.twentyleke.feature.scan.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ScanningIndicator() {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .padding(8.dp)
    ) {
        val width = size.width
        val height = size.height

        val bottomLeftCorner = Path().apply {
            moveTo(width.times(.0f), height.times(.75f))
            quadraticBezierTo(
                width.times(.0f),
                height.times(1f),
                width.times(0.25f),
                height.times(1f)
            )
        }
        val bottomRightCorner = Path().apply {
            moveTo(width.times(1f), height.times(.75f))
            quadraticBezierTo(
                width.times(1f),
                height.times(1f),
                width.times(0.75f),
                height.times(1f)
            )
        }
        val topRightCorner = Path().apply {
            moveTo(width.times(0.75f), height.times(0f))
            quadraticBezierTo(
                width.times(1f),
                height.times(0f),
                width.times(1f),
                height.times(0.25f)
            )
        }
        val topLeftCorner = Path().apply {
            moveTo(width.times(0f), height.times(.25f))
            quadraticBezierTo(
                width.times(0f),
                height.times(0f),
                width.times(0.25f),
                height.times(0f)
            )
        }

        drawPath(
            path = bottomLeftCorner,
            color = Color.White,
            style = Stroke(width = 20f, cap = StrokeCap.Round)
        )
        drawPath(
            path = bottomRightCorner,
            color = Color.White,
            style = Stroke(width = 20f, cap = StrokeCap.Round)
        )
        drawPath(
            path = topRightCorner,
            color = Color.White,
            style = Stroke(width = 20f, cap = StrokeCap.Round)
        )
        drawPath(
            path = topLeftCorner,
            color = Color.White,
            style = Stroke(width = 20f, cap = StrokeCap.Round)
        )
    }
}

@Preview
@Composable
fun PreviewScanningIndicator() {
    ScanningIndicator()
}