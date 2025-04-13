package com.poly.herewego.presentation.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.poly.herewego.presentation.widgets.Title
import com.poly.herewego.ui.component.AllStamp
import com.poly.herewego.ui.component.MyProfile
import com.poly.herewego.ui.theme.AppTheme
import com.poly.herewego.ui.utils.DrawableManager
import com.poly.herewego.ui.utils.dashedBorder

@Composable
fun ProfileScreen(name: String, onOpenCategory: (category: String) -> Unit, onAddTour: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp, start = 12.dp, end = 12.dp, bottom = 12.dp)
    ) {
        Title("Profile")
        MyProfile("", "Tout vos tampons :)", { })
//        AllStamp("")
//        Row (Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
//            Button({}) { Text("Partager") }
//        }
        androidx.compose.material3.Button(onClick = {}, Modifier.padding(top = 12.dp)) {
            Text("Voir tout les lieux disponibles")
        }
        androidx.compose.material3.Button(onClick = {}, Modifier.padding(top = 12.dp)) {
            Text("Voir tout les passeports")
        }
        androidx.compose.material3.Button(onClick = {}, Modifier.padding(top = 12.dp)) {
            Text("Voir ma liste d'amis")
        }
        androidx.compose.material3.Button(onClick = {}, Modifier.padding(top = 12.dp)) {
            Text("Envoyer une invitation à un ami")
        }
        androidx.compose.material3.Button(onClick = {}, Modifier.padding(top = 12.dp)) {
            Text("Voir le profil d'un ami")
        }
        androidx.compose.material3.Button(onClick = {}, Modifier.padding(top = 12.dp)) {
            Text("Envoyer un passport à un ami")
        }
//        androidx.compose.material3.Button(onClick = {}, Modifier.padding(top = 12.dp)) {
//            Text("Créer un passport à un ami")
//        }
//        androidx.compose.material3.Button(onClick = {}, Modifier.padding(top = 12.dp)) {
//            Text("Ajouter un lieu")
//        }
    }
}
//
//@SuppressLint("DefaultLocale")
//@Composable
//fun Badge(name: String, percent: Double, check: Boolean, listener: () -> Unit) {
//    Card(
//        shape = RoundedCornerShape(8.dp),
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable(onClick = listener)
//    ) {
//        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
//            Image(
//                painter = painterResource(DrawableManager.getCategoryIcon(name)),
//                contentDescription = "avatar",
//                modifier = Modifier
//                    .size(48.dp)
//                    .clip(CircleShape)
//            )
//            Box(Modifier.width(12.dp))
//            Column {
//                Text(name.uppercase(), maxLines = 2, overflow = TextOverflow.Ellipsis, textAlign = TextAlign.Center, style = MaterialTheme.typography.bodyLarge)
//                LinearProgressIndicator(progress = { percent.toFloat() }, modifier = Modifier.height(8.dp), strokeCap = StrokeCap.Round)
//            }
////                Box(Modifier.align(Alignment.Bottom)) {
////                    Text(String.format("%.0f", percent * 100) + "%", maxLines = 2, overflow = TextOverflow.Ellipsis, textAlign = TextAlign.Center)
////                }
//            Spacer(modifier = Modifier.weight(1f))
//            Icon(Icons.AutoMirrored.Rounded.KeyboardArrowRight, contentDescription = "icon")
//        }
//    }
//}
//
//@SuppressLint("DefaultLocale")
//@Composable
//fun BadgeAdd(listener: () -> Unit) {
//    Card(
//        shape = RoundedCornerShape(8.dp),
//        colors = CardColors(
//            containerColor = Color.Transparent,
//            contentColor = MaterialTheme.colorScheme.primary,
//            disabledContainerColor = Color.Transparent,
//            disabledContentColor = Color.Transparent
//        ),
//        modifier = Modifier
//            .fillMaxWidth()
//            .dashedBorder(
//                color = MaterialTheme.colorScheme.surfaceTint,
//                shape = RoundedCornerShape(12.dp)
//            )
//            .clickable(onClick = listener)
//    ) {
//        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
//            Icon(
//                Icons.Rounded.Add, contentDescription = "icon",
//                modifier = Modifier
//                    .clip(CircleShape)
//            )
//            Box(Modifier.width(12.dp))
//            Column {
//                Text("Add a tour", maxLines = 2, overflow = TextOverflow.Ellipsis, textAlign = TextAlign.Center, style = MaterialTheme.typography.bodyLarge)
//            }
//            Spacer(modifier = Modifier.weight(1f))
////            Icon(Icons.Rounded.FavoriteBorder, contentDescription = "icon")
//        }
//    }
//}

@Preview
@Composable
fun DefaultPreview() {
    AppTheme {
        ProfileScreen("name", {}, {})
    }
}

