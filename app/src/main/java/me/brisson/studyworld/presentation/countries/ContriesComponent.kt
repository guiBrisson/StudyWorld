package me.brisson.studyworld.presentation.countries

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import me.brisson.studyworld.R
import me.brisson.studyworld.domain.model.Country
import me.brisson.studyworld.ui.theme.StudyWorldTheme
import java.text.DecimalFormat

@Composable
fun CountryItem(
    modifier: Modifier = Modifier,
    country: Country
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            contentColor = MaterialTheme.colorScheme.onBackground,
            containerColor = MaterialTheme.colorScheme.background
        ),
        shape = RectangleShape
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            country.flagImageUrl?.let { flagImageUrl ->
                AsyncImage(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(170.dp),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(flagImageUrl)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.ic_launcher_background),
                    contentDescription = "${country.name} flag",
                    contentScale = ContentScale.Crop,
                )

                Column(modifier = Modifier.padding(start = 10.dp)) {
                    Text(
                        modifier = Modifier.padding(bottom = 4.dp),
                        text = country.name.orEmpty(),
                        style = MaterialTheme.typography.titleLarge,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                    CountryInfo(
                        text = "Population: ",
                        info = country.population?.thousandsFormat().orEmpty()
                    )
                    CountryInfo(text = "Region: ", info = country.region.orEmpty())
                    CountryInfo(text = "Capital: ", info = country.capital?.first().orEmpty())

                }
            }
        }
    }
}

private fun Int.thousandsFormat() =
    DecimalFormat("#,###")
        .format(this)
        .replace(",", ".")

@Composable
private fun CountryInfo(
    modifier: Modifier = Modifier,
    text: String,
    info: String
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(
            modifier = Modifier.padding(end = 1.dp),
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )

        Text(
            text = info,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Preview
@Composable
private fun PreviewCountryItem() {
    StudyWorldTheme {
        val brazil = Country(
            name = "Brazil",
            region = "America",
            subRegion = "South America",
            population = 2000000,
            flagImageUrl = "",
            area = 2.0,
            capital = listOf("Brasilia"),
            coatOfArmsImageUrl = "",
            independent = true,
            latLong = emptyList(),
            continents = emptyList(),
            mapUrl = ""
        )
        CountryItem(country = brazil)
    }
}