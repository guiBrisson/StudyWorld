package me.brisson.studyworld.data.api.entity

import com.google.gson.annotations.SerializedName

data class DemonymsEntity(
    @SerializedName(value = "eng", alternate = ["eng", "fra"])
    val demonyms: List<DemonymEntity>
)

data class DemonymEntity(
    val f: String?,
    val m: String?
)