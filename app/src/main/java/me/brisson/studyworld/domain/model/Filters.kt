package me.brisson.studyworld.domain.model

enum class RegionsFilter(name: String) {
    All("All"),
    Africa("Africa"),
    America("America"),
    Asia("Asian"),
    Europe("Europe"),
    Oceania("Oceania");

    companion object {
        fun allRegions(): List<RegionsFilter> = enumValues<RegionsFilter>().toList()
    }
}