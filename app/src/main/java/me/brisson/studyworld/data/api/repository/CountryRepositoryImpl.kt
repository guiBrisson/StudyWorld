package me.brisson.studyworld.data.api.repository

import me.brisson.studyworld.data.api.entity.toCountry
import me.brisson.studyworld.data.api.service.CountryService
import me.brisson.studyworld.domain.model.Country
import me.brisson.studyworld.domain.model.RegionsFilter
import me.brisson.studyworld.domain.model.Response
import me.brisson.studyworld.domain.repository.CountryRepository
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    private val countryService: CountryService
) : CountryRepository {

    override suspend fun getAllCountries(query: String?): Response<List<Country>> {
        return try {
            val countries = countryService.getAllCountries().map { it.toCountry() }
            if (!query.isNullOrEmpty()) {
                val filtered = countries.filter { it.name?.contains(query, ignoreCase = true) == true }
                return Response.Success(filtered)
            }

            Response.Success(countries)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Error(e.message ?: "Unknown error")
        }
    }

    override suspend fun getCountriesByName(name: String): Response<List<Country>> {
        return try {
            val countries = countryService.getCountriesByName(name).map { it.toCountry() }
            Response.Success(countries)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Error(e.message ?: "Unknown error")
        }
    }

    override suspend fun getCountriesByLanguage(language: String): Response<List<Country>> {
        return try {
            val countries = countryService.getCountriesByLanguage(language).map { it.toCountry() }
            Response.Success(countries)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Error(e.message ?: "Unknown error")
        }
    }

    override suspend fun getCountriesByRegion(
        region: RegionsFilter,
        query: String?
    ): Response<List<Country>> {
        return try {
            if (region == RegionsFilter.All) {
                return getAllCountries(query = query)
            }

            val countries = countryService.getCountriesByRegion(region.name).map { it.toCountry() }
            if (!query.isNullOrEmpty()) {
                val filtered = countries.filter { it.name?.contains(query, ignoreCase = true) == true }
                return Response.Success(filtered)
            }

            Response.Success(countries)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Error(e.message ?: "Unknown error")
        }
    }
}
