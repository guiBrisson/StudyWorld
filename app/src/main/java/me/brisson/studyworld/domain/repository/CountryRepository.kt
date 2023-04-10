package me.brisson.studyworld.domain.repository

import me.brisson.studyworld.domain.model.Country
import me.brisson.studyworld.domain.model.RegionsFilter
import me.brisson.studyworld.domain.model.Response

interface CountryRepository {
    suspend fun getAllCountries(query: String? = null): Response<List<Country>>

    suspend fun getCountriesByName(name: String): Response<List<Country>>

    suspend fun getCountriesByLanguage(language: String): Response<List<Country>>

    suspend fun getCountriesByRegion(region: RegionsFilter, query: String? = null): Response<List<Country>>
}
