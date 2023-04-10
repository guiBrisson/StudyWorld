package me.brisson.studyworld.data.api.service

import me.brisson.studyworld.data.api.entity.CountryEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryService {

    @GET("all")
    suspend fun getAllCountries(): List<CountryEntity>

    @GET("name/{name}")
    suspend fun getCountriesByName(@Path("name") name: String): List<CountryEntity>

    @GET("lang/{lang}")
    suspend fun getCountriesByLanguage(@Path("lang") language: String): List<CountryEntity>

    @GET("region/{region}")
    suspend fun getCountriesByRegion(@Path("region") region: String): List<CountryEntity>
}