package me.brisson.studyworld.presentation.countries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.brisson.studyworld.domain.model.RegionsFilter
import me.brisson.studyworld.domain.model.Response
import me.brisson.studyworld.domain.repository.CountryRepository
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val countryRepository: CountryRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(CountriesUiState())
    val uiState: StateFlow<CountriesUiState> = _uiState.asStateFlow()

    init {
        fetchCountriesByRegion(RegionsFilter.All)
    }

    fun fetchCountriesByRegion(region: RegionsFilter, query: String? = null) {
        _uiState.update {
            it.copy(countriesError = null, countries = emptyList(), countriesLoading = true)
        }

        viewModelScope.launch {
            when (val result = countryRepository.getCountriesByRegion(region = region, query)) {
                is Response.Success -> {
                    result.data?.let { countries ->
                        _uiState.update {
                            it.copy(countriesLoading = false, countries = countries)
                        }
                    }
                }
                is Response.Error -> {
                    _uiState.update {
                        it.copy(countriesLoading = false, countriesError = result.message)
                    }
                }
            }
        }
    }
}