package com.poly.herewego.presentation.passport

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.poly.herewego.data.Passport
import com.poly.herewego.presentation.widgets.Medal
import com.poly.herewego.ui.theme.AppTheme
import com.poly.herewego.ui.component.PassportWidget
import com.poly.herewego.ui.component.PlaceItem
import com.poly.herewego.ui.utils.hexStringToColor
import kotlin.random.Random

@Composable
fun PassportScreen(
    passportId: String,
    onPoiClick: (place: String) -> Unit,
    viewModel: PassportViewModel = hiltViewModel()
) {
    val passportState by viewModel.uiState.collectAsState()

    LaunchedEffect(passportId) {
        viewModel.fetchData(passportId)
    }

    when (val state = passportState) {
        is PassportUiState.Loading -> CircularProgressIndicator()
        is PassportUiState.Success -> RenderSuccess(state.passport, onPoiClick)
        is PassportUiState.Error -> Text("Passport not found")
    }
}

@Composable
fun RenderSuccess(passport: Passport, onPoiClick: (place: String) -> Unit) {
    val maxImageHeight = 250.dp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(maxImageHeight + 50.dp)
                .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
                .background(Color.White)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                model = passport.imageUrl,
                contentScale = ContentScale.Crop,
                contentDescription = "${passport.name} illustration"
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(maxImageHeight)
                )
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                        .background(Color.White)
                        .padding(16.dp)
                ) {
                    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                        Column {
                            Text(
                                text = passport.name,
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Text("5 / ${passport.places.size} sites visités")
                        }
                        Medal(passport.color.hexStringToColor())
                        PassportWidget(passport, passport.name, passport.color, passport.icon, {}, true)
                    }

                    Box(Modifier.height(12.dp))
                    Text(
                        text = "Liste des points d'interets",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }
            }

            items(passport.places.size) { index ->
                PlaceItem(passport.places[index].name, passport.icon, passport.name, passport.color, Random.Default.nextBoolean(), onPoiClick)
            }

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(16.dp)
                ) {
                    HistoryWidget()
                    Spacer(Modifier.height(12.dp))
                    Text(
                        text = """
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor
                            incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
                            exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute
                            irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla
                            pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia
                            deserunt mollit anim id est laborum.
                            
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor
                            incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
                            exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute
                            irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla
                            pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia
                            deserunt mollit anim id est laborum.
                        """.trimIndent(),
                        style = MaterialTheme.typography.bodyMedium,
                        lineHeight = 24.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun HistoryWidget() {
    Column {
        Text(
            text = "Historique",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text("Check elephant le 12/04/2025")
        Text("Check elephant le 15/05/2025")
    }
}

@Preview
@Composable
fun PreviewScroll() {
    val passports = listOf(
        Passport("id", "date", "Nantes", "0xFF00BCD4", "\uD83D\uDC18", 0f, 0f, "", listOf()),
        Passport("id", "date", "Nantes", "0xFF3F78B5", "\uD83D\uDDFC", 0f, 0f, "", listOf())
    )

    AppTheme {
        RenderSuccess(passports[0], {})
    }
}
