package com.yuriishcherbyna.moviessho.model

import com.squareup.moshi.Json

data class Cast(
    val id: String,
    val name: String,
    @Json(name = "profile_path") val profilePath: String?
)