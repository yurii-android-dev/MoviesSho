package com.yuriishcherbyna.moviessho.model

import com.squareup.moshi.Json

data class Cast(
    val name: String,
    @Json(name = "profile_path") val profilePath: String
)