package org.istheshit.turn.datacollector.domain

import java.time.ZonedDateTime
import javax.persistence.*

@Entity
@Table(name="measurement")
data class Measurement(
        @Id
        @GeneratedValue(strategy= GenerationType.IDENTITY)
        val id: Long? = null,

        val boatName: String,

        val name: String,
        val value: Double,

        val coreId: String,
        val publishedAt: ZonedDateTime
)