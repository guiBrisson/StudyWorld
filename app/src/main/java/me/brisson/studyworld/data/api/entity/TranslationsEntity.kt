package me.brisson.studyworld.data.api.entity

import com.google.gson.annotations.SerializedName

data class TranslationsEntity(
    @SerializedName(
        value = "eng",
        alternate = ["ara", "bre", "ces", "cym", "deu", "est", "fin", "fra", "hrv", "hun", "ita",
            "jpn", "kor", "nld", "per", "pol", "por", "rus", "slk", "spa", "srp", "swe", "tur",
            "urd", "zho"]
    )
    val language: LanguageEntity?
)