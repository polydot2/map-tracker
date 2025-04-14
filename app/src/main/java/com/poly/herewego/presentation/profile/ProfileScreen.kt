package com.poly.herewego.presentation.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.poly.herewego.presentation.widgets.Title
import com.poly.herewego.ui.component.MyProfile
import com.poly.herewego.ui.theme.AppTheme

@Composable
fun ProfileScreen(name: String, onOpenCategory: (category: String) -> Unit, onAddTour: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp, start = 12.dp, end = 12.dp, bottom = 12.dp)
    ) {
        Title("Profile")
        MyProfile("", "124 lieux visités !", { })
        Text("Historique des derniers tampons !")
        Button(onClick = {}, Modifier.padding(top = 12.dp)) {
            Text("Voir tout les lieux visités")
        }
        Button(onClick = {}, Modifier.padding(top = 12.dp)) {
            Text("Partager ma carte touristique")
        }
        Button(onClick = {}, Modifier.padding(top = 12.dp)) {
            Text("Voir tout les lieux disponibles")
        }
        Button(onClick = {}, Modifier.padding(top = 12.dp)) {
            Text("Voir tout les passeports")
        }
        Button(onClick = {}, Modifier.padding(top = 12.dp)) {
            Text("Ajouter un ami")
        }
        Button(onClick = {}, Modifier.padding(top = 12.dp)) {
            Text("Voir ma liste d'amis")
        }
        Button(onClick = {}, Modifier.padding(top = 12.dp)) {
            Text("Voir le profil d'un ami")
        }
        Button(onClick = {}, Modifier.padding(top = 12.dp)) {
            Text("Envoyer un passport à un ami")
        }
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

