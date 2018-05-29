package org.istheshit.turn.datacollector.domain

import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.serialization.Serializable
import java.time.ZonedDateTime

@Serializable
data class MeasurementDto(

    val event: String,
    val data: Double,

    @JsonProperty("coreid")
    val coreId: String,
    @JsonProperty("published_at")
    val publishedAt: ZonedDateTime
)