package org.istheshit.turn.datacollector.beans

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.apache.camel.Body
import org.apache.camel.Headers
import org.apache.camel.component.google.pubsub.GooglePubsubConstants
import org.istheshit.turn.datacollector.domain.Device
import org.istheshit.turn.datacollector.domain.Location
import org.istheshit.turn.datacollector.repositories.LocationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.ZonedDateTime

@Component
class DeviceLocationHandler(
    @Autowired val locationRepository: LocationRepository
) {

    fun process(@Body data: String, @Headers headers: Map<String, Any>): Map<String, Any> {

        val attributes = headers[GooglePubsubConstants.ATTRIBUTES] as Map<String, String>
        val deviceId = attributes["device_id"] ?: error("Event did not contain device_id attribute")
        val event = attributes["event"] ?: error("Event did not contain event attribute")
        val published = ZonedDateTime.parse(attributes["published_at"])
        val messageId = headers[GooglePubsubConstants.MESSAGE_ID] as String

        val locationDto: LocationDto = ObjectMapper().readValue(data)

        locationRepository.save(
            Location(
                id = null,
                boatName = Device.findName(deviceId),
                coreId = deviceId,
                publishedAt = published,
                messageId = messageId,
                latitude = locationDto.lat.toDouble(),
                longitude = locationDto.lon.toDouble(),
                accuracy = locationDto.accuracy.toDouble()
            )
        )

        return mapOf(
            Pair("device", Device.findName(deviceId)),
            Pair("deviceId", deviceId),
            Pair("event", event),
            Pair("location", locationDto),
            Pair("timestamp", published)
        )
    }

}

class LocationDto {
    lateinit var lat: String
    lateinit var lon: String
    lateinit var accuracy: String
}
