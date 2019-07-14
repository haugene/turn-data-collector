package org.istheshit.turn.datacollector.domain

import java.time.ZonedDateTime
import javax.persistence.*

@Entity
@Table(name="location")
data class Location(
        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        val id: Long? = null,

        val boatName: String,
        val coreId: String,
        val publishedAt: ZonedDateTime,
        val messageId: String,

        val latitude: Double,
        val longitude: Double,
        val accuracy: Double
)