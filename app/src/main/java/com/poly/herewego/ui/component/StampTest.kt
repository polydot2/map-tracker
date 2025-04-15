package com.poly.herewego.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.nativeCanvas // Import explicite pour nativeCanvas
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.random.Random
import androidx.compose.material3.Text
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.graphicsLayer
import android.graphics.Paint
import android.graphics.PathEffect
import android.graphics.Path as AndroidPath
import android.graphics.RectF
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.poly.herewego.ui.theme.AppTheme
import com.poly.herewego.ui.utils.hexStringToColor

@Composable
fun VisitedStamp(
    date: String = "12/12/2025",
    name: String = "Nantes",
    emoji: String = "\uD83D\uDC18",
    colorPass: String,
    modifier: Modifier = Modifier
) {
    val stampSize = 64.dp
    val outerRadius = 58f
    val innerRadius = 64f
    val textColor = colorPass.hexStringToColor()
    val borderColor = textColor
    val random = Random.Default

    Box(
        modifier = modifier
            .size(stampSize)
            .rotate(random.nextFloat() * 24f - 12f),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.size(stampSize)) {
            val center = Offset(size.width / 2, size.height / 2)
            // Cercle extérieur avec effet aléatoire
            val segments = 20
            repeat(segments) { i ->
                val angle = (i * 360f / segments).toFloat()
                val randomness = random.nextFloat() * 0.3f + 0.7f
                val segmentLength = random.nextFloat() * 4f + 4f
                val isDashed = random.nextBoolean()

                rotate(angle, pivot = center) {
                    if (isDashed) {
                        drawCircle(
                            color = borderColor,
                            radius = 2f * randomness,
                            center = Offset(center.x, center.y - outerRadius),
                            alpha = 0.8f
                        )
                    } else {
                        drawArc(
                            color = borderColor,
                            startAngle = 0f,
                            sweepAngle = segmentLength * randomness,
                            useCenter = false,
                            topLeft = Offset(
                                center.x - outerRadius,
                                center.y - outerRadius
                            ),
                            size = androidx.compose.ui.geometry.Size(outerRadius * 2, outerRadius * 2),
                            style = Stroke(width = 6f * randomness)
                        )
                    }
                }
            }

//            drawCircle(
//                color = borderColor,
//                radius = innerRadius,
//                center = center,
//                style = Stroke(width = 4f),
//            )

            // Date en arc de cercle
            val textPaint = Paint().apply {
                color = textColor.toArgb()
                textSize = 12.sp.toPx()
                textAlign = Paint.Align.CENTER
                typeface = android.graphics.Typeface.DEFAULT_BOLD
            }
            val radius = innerRadius - 20f
            val textPath = AndroidPath().apply {
                addArc(
                    RectF(
                        center.x - radius,
                        center.y - radius,
                        center.x + radius,
                        center.y + radius
                    ),
                    180f, // Début en haut
                    180f // Arc sur la moitié inférieure
                )
            }
            drawIntoCanvas {
                it.nativeCanvas.drawTextOnPath(
                    name,
                    textPath,
                    0f,
                    0f,
                    textPaint
                )
            }
        }

        // Emoji
        Box(
            modifier = Modifier.size(stampSize),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = emoji,
                style = TextStyle(
                    color = textColor,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .graphicsLayer(alpha = 0.99f)
                    .drawWithCache {
                        val brush = Brush.horizontalGradient(
                            listOf(textColor, textColor)
                        )
                        onDrawWithContent {
                            drawContent()
                            drawRect(brush, blendMode = BlendMode.SrcAtop)
                        }
                    }
            )
        }

        Box(
            modifier = Modifier
                .size(stampSize)
                .offset(y = (-10).dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Text(
                date,
                style = TextStyle(
                    color = textColor,
                    fontSize = 10.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                ),
            )
        }
    }
}

@Composable
fun PlaceItem(name: String, icon: String, passportName: String, color: String, isVisited: Boolean, onClick: (place: String) -> Unit) {
    Box(
        Modifier
            .background(color = Color.White)
            .padding(bottom = 12.dp)
    ) {
        Card(
            shape = RoundedCornerShape(bottomStart = 12.dp, topStart = 12.dp, topEnd = 32.dp, bottomEnd = 32.dp),
            modifier = Modifier
                .height(64.dp)
                .padding(start = 12.dp, end = 12.dp)
                .fillMaxWidth()
                .clickable(onClick = { onClick(name) })
        ) {
            Row(Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween, Alignment.CenterVertically) {
                Text(name, modifier = Modifier.padding(12.dp))
//                Checkbox(checked = checked, enabled = false, onCheckedChange = {})
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .background(color = Color.Black.copy(alpha = 0.1f), shape = CircleShape)
                ) {
                    if (isVisited)
                        VisitedStamp(emoji = icon, name = name, colorPass = color)
                }
            }
        }
    }
}

@Preview
@Composable
fun Stamp() {
    AppTheme {
        Column(Modifier, Arrangement.SpaceBetween) {
            PlaceItem("Name", "i", "pass", "0xFFFFF", true, {})
//            PlaceItem("Name", false, {})
//            VisitedStamp(emoji = "\uD83D\uDC18", useRedText = true) // Emoji et date en rouge
//            VisitedStamp(emoji = "\uD83D\uDC18", date = "01/01/2023", useRedText = false) // Emoji et date en noir
        }
    }
}
