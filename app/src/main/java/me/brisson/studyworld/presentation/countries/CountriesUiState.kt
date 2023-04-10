package me.brisson.studyworld.presentation.countries

import me.brisson.studyworld.domain.model.Country

data class CountriesUiState(
    val countries: List<Country> = emptyList(),
    val countriesError: String? = null,
    val countriesLoading: Boolean = true,
)
