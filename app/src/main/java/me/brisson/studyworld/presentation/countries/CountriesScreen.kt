package me.brisson.studyworld.presentation.countries

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import me.brisson.studyworld.domain.model.Country
import me.brisson.studyworld.ui.theme.StudyWorldTheme
import me.brisson.studyworld.R
import me.brisson.studyworld.domain.model.RegionsFilter
import me.brisson.studyworld.ui.theme.ThemeState

@Composable
fun CountriesScreen(
    modifier: Modifier = Modifier,
    viewModel: CountriesViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    CountriesScreen(
        modifier = modifier,
        countries = uiState.countries,
        loading = uiState.countriesLoading,
        filterRegion = viewModel::fetchCountriesByRegion
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CountriesScreen(
    modifier: Modifier = Modifier,
    countries: List<Country>,
    loading: Boolean = false,
    filterRegion: (region: RegionsFilter, query: String) -> Unit,
) {
    var searchText by remember { mutableStateOf("") }
    var countryDropboxExpanded by remember { mutableStateOf(false) }
    var selectedRegion by remember { mutableStateOf(RegionsFilter.All) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Where in the world?", fontSize = 20.sp) },
                actions = {
                    IconButton(onClick = {
                        val isDark = ThemeState.isDark()
                        ThemeState.isDark(!isDark)
                    }) {
                        val icon = if (ThemeState.isDark()) {
                            painterResource(id = R.drawable.ic_sun)
                        } else {
                            painterResource(id = R.drawable.ic_moon)
                        }
                        Icon(
                            modifier = Modifier.size(18.dp),
                            painter = icon,
                            contentDescription = "Theme toggle"
                        )
                    }
                }
            )
        }
    ) { scaffoldPadding ->

        LazyColumn(
            modifier = modifier
                .padding(scaffoldPadding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            if (loading) {
                item {
                    LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                }
            }

            item {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    value = searchText,
                    onValueChange = {
                        searchText = it
                    },
                    label = {
                        Text(text = "Search country")
                    },
                    placeholder = {
                        Text(text = "Brazil, Russia, India...")
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Rounded.Search, contentDescription = "Search icon")
                    },
                    keyboardActions = KeyboardActions(onSearch = {
                        filterRegion(selectedRegion, searchText)
                    }),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search)
                )
            }

            item {
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .width(IntrinsicSize.Min)
                ) {
                    val interactionSource = remember { MutableInteractionSource() }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .width(IntrinsicSize.Max)
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null,
                                onClick = { countryDropboxExpanded = true }
                            ),
                        horizontalArrangement = Arrangement.End
                    ) {
                        val iconRotation by animateFloatAsState(
                            if (countryDropboxExpanded) 0f else 180f
                        )

                        val text = if (selectedRegion == RegionsFilter.All) {
                            "Filter by Region"
                        } else {
                            "Region: ${selectedRegion.name}"
                        }

                        Text(text = text)
                        Icon(
                            modifier = Modifier
                                .padding(start = 18.dp)
                                .rotate(iconRotation),
                            imageVector = Icons.Rounded.ArrowDropDown,
                            contentDescription = "Arrow dropdown icon"
                        )
                    }
                    DropdownMenu(
                        modifier = Modifier
                            .width(200.dp)
                            .background(MaterialTheme.colorScheme.background)
                            .padding(horizontal = 10.dp),
                        expanded = countryDropboxExpanded,
                        onDismissRequest = { countryDropboxExpanded = false },
                    ) {
                        val regions = RegionsFilter.allRegions()
                        regions.forEach { region ->
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = region.name,
                                        color = MaterialTheme.colorScheme.onBackground
                                    )
                                },
                                onClick = {
                                    if (selectedRegion != region) {
                                        filterRegion(region, searchText)
                                        selectedRegion = region
                                    }
                                    countryDropboxExpanded = false
                                }
                            )
                        }
                    }
                }
            }

            items(countries) { country ->
                CountryItem(
                    modifier = Modifier.padding(vertical = 6.dp, horizontal = 10.dp),
                    country = country
                )
            }
        }
    }

}

@Preview
@Composable
fun PreviewCountriesScreen() {
    StudyWorldTheme {
        CountriesScreen(countries = emptyList(), filterRegion = { _, _ -> })
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewCountriesScreenDark() {
    StudyWorldTheme {
        CountriesScreen(countries = emptyList(), filterRegion = { _, _ -> })
    }
}