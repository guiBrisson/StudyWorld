package me.brisson.studyworld.domain.model

data class Country(
    val name: String?,
    val region: String?,
    val subRegion: String?,
    val population: Int?,
    val flagImageUrl: String?,
    val area: Double?,
    val capital: List<String>?,
    val coatOfArmsImageUrl: String?,
    val independent: Boolean?,
    val latLong: List<Double>?,
    val continents: List<String>?,
    val mapUrl: String?
)
