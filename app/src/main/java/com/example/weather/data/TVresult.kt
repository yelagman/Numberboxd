package com.example.weather.data


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TVresult(
    @SerialName("page")
    var page: Int? = null,
    @SerialName("results")
    var results: List<Result?>? = null,
    @SerialName("total_pages")
    var totalPages: Int? = null,
    @SerialName("total_results")
    var totalResults: Int? = null
)