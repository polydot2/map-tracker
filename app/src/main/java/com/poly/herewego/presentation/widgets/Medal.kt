package com.poly.herewego.presentation.widgets

import android.graphics.Typeface
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.poly.herewego.ui.theme.AppTheme
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun Medal(
    color: Color = Color(0xFFFF0000)
) {
    val textMeasurer = rememberTextMeasurer()

    Canvas(
        modifier = Modifier.size(64.dp, 64.dp)
    ) {
        val centerX = size.width / 2
        val centerY = size.height / 2 - size.width * 0.1f
        val outerRadius = size.width * 0.4f
        val innerRadius = outerRadius * 0.75f
        val circleRadius = outerRadius * 0.8f
        val circle2Radius = outerRadius * 0.65f

        // Rubans en bas
        val ribbonWidth = size.width * 0.28f
        val ribbonHeight = size.height * 0.26f
        val ribbonPathLeft = Path().apply {
            moveTo(centerX - ribbonWidth / 2, centerY)
            lineTo(centerX - ribbonWidth / 2, centerY + circleRadius + ribbonHeight)
            lineTo(centerX, centerY + circleRadius + ribbonHeight - ribbonHeight * 0.3f)
            lineTo(centerX + ribbonWidth / 2, centerY + circleRadius + ribbonHeight)
            lineTo(centerX + ribbonWidth / 2, centerY)
            close()
        }

        rotate(28f) {
            drawPath(
                path = ribbonPathLeft,
//                style = Stroke(),
                color = color
            )
        }
        rotate(-28f) {
            drawPath(
                path = ribbonPathLeft,
//                style = Stroke(),
                color = color.copy(
                    color.alpha,
                    red = (color.red * 0.8f).coerceIn(0f, 1f),
                    green = (color.green * 0.8f).coerceIn(0f, 1f),
                    blue = (color.blue * 0.8f).coerceIn(0f, 1f)
                )
            )
        }

        // Dessiner le badge en forme de soleil (étoiles pointues)
        val points = 8 // Nombre de pointes
        val path = Path().apply {
            for (i in 0 until points * 2) {
                val angle = Math.PI / points * i
                val radius = if (i % 2 == 0) outerRadius else innerRadius
                val x = centerX + (radius * cos(angle)).toFloat()
                val y = centerY + (radius * sin(angle)).toFloat()
                if (i == 0) moveTo(x, y) else lineTo(x, y)
            }
            close()
        }

        // Cercle central
        drawCircle(
            brush = Brush.linearGradient(
                colors = listOf(Color(0xFFFFD700), Color(0xFFDAA520)),
                start = Offset(centerX, centerY - circleRadius),
                end = Offset(centerX, centerY + circleRadius)
            ),
            radius = circleRadius,
            center = Offset(centerX, centerY)
        )

        drawPath(
            path = path,
            brush = Brush.linearGradient(
                colors = listOf(Color(0xFFFFD700), Color(0xFFDAA520)),
                start = Offset(centerX, centerY - outerRadius),
                end = Offset(centerX, centerY + outerRadius)
            )
        )

        // Cercle central
        drawCircle(
            brush = Brush.linearGradient(
                colors = listOf(Color(0xFFFFF9C4), Color(0xFFF0E68C)),
                start = Offset(centerX, centerY - circle2Radius),
                end = Offset(centerX, centerY + circle2Radius)
            ),
            radius = circle2Radius,
            center = Offset(centerX, centerY)
        )

        // Texte "100%" (sur l'étoile)
        drawIntoCanvas {
            val textLayoutResult = textMeasurer.measure(
                text = "100%",
                style = TextStyle(
                    color = Color(0xFF4B0082),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            val textWidth = textLayoutResult.size.width
            val textHeight = textLayoutResult.size.height
            drawText(
                textLayoutResult,
                topLeft = Offset(
                    centerX - textWidth / 2,
                    centerY - textHeight / 2
                )
            )
        }
    }
}

@Preview
@Composable
fun MedalPreview() {
    AppTheme {
        Medal()
    }
}