package org.istheshit.turn.datacollector.beans

import org.apache.camel.Body
import org.apache.camel.Headers
import org.apache.camel.component.google.pubsub.GooglePubsubConstants
import org.istheshit.turn.datacollector.domain.Device
import org.istheshit.turn.datacollector.domain.Measurement
import org.istheshit.turn.datacollector.repositories.MeasurementRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.ZonedDateTime

@Component
class DeviceMetricHandler(
    @Autowired val measurementRepository: MeasurementRepository
) {

    fun process(@Body data: String, @Headers headers: Map<String, Any>): Map<String, Any> {

        val attributes = headers[GooglePubsubConstants.ATTRIBUTES] as Map<String, String>
        val deviceId = attributes["device_id"] ?: error("Event did not contain device_id attribute")
        val event = attributes["event"] ?: error("Event did not contain event attribute")
        val published = ZonedDateTime.parse(attributes["published_at"])
        val messageId = headers[GooglePubsubConstants.MESSAGE_ID] as String

        measurementRepository.save(Measurement(
            id = null,
            boatName = Device.findName(deviceId),
            name = event,
            value = data.toDouble(),
            coreId = deviceId,
            publishedAt = published,
            messageId = messageId
        ))

        return mapOf(
            Pair("device", Device.findName(deviceId)),
            Pair("deviceId", deviceId),
            Pair("event", event),
            Pair("value", data),
            Pair("timestamp", published)
        )
    }

}