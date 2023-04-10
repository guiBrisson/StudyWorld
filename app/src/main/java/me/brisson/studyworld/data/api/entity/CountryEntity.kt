package me.brisson.studyworld.data.api.entity

import me.brisson.studyworld.domain.model.Country

data class CountryEntity(
    val altSpellings: List<String?>?,
    val area: Double?,
    val capital: List<String>?,
    val capitalInfo: CapitalInfoEntity?,
    val car: CarEntity?,
    val cca2: String?,
    val cca3: String?,
    val ccn3: String?,
    val cioc: String?,
    val coatOfArms: CoatOfArmsEntity?,
    val continents: List<String>?,
    val currencies: CurrenciesEntity?,
//    val demonyms: DemonymsEntity?,
    val fifa: String?,
    val flag: String?,
    val flags: FlagsEntity?,
    val idd: IddEntity?,
    val independent: Boolean?,
    val landlocked: Boolean?,
//    val languages: LanguagesEntity?,
    val latlng: List<Double>?,
    val maps: MapsEntity?,
    val name: NameEntity?,
    val population: Int?,
    val postalCode: PostalCodeEntity?,
    val region: String?,
    val startOfWeek: String?,
    val status: String?,
    val subregion: String?,
    val timezones: List<String?>?,
    val tld: List<String?>?,
//    val translations: TranslationsEntity?,
    val unMember: Boolean?
)

fun CountryEntity.toCountry() =
    Country(
        name = "${this.name?.common ?: this.name?.official} $flag",
        region = this.region,
        subRegion = this.subregion,
        population = this.population,
        flagImageUrl = this.flags?.png,
        area = this.area,
        capital = this.capital,
        coatOfArmsImageUrl = this.coatOfArms?.png,
        independent = this.independent,
        latLong = this.latlng,
        continents = this.continents,
        mapUrl = this.maps?.googleMaps ?: this.maps?.openStreetMaps
    )