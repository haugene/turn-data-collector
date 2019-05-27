package org.istheshit.turn.datacollector.routes

import org.apache.camel.LoggingLevel
import org.apache.camel.spring.SpringRouteBuilder
import org.istheshit.turn.datacollector.beans.DeviceMetricHandler
import org.springframework.stereotype.Component
import java.lang.Exception

@Component
class CoreRoute : SpringRouteBuilder() {
    private final val loggerName = CoreRoute::class.java.toGenericString()


    companion object {
        const val PUBSUB_EVENT = "google-pubsub:haugene:process-particle-events?connectionFactory=#pubSubConnectionFactory"
    }

    override fun configure() {

        onException(Exception::class.java)
            .log(LoggingLevel.ERROR, loggerName, "Failed to process event, message data: \${body}")
            .logStackTrace(true)
            .handled(true)

        from(PUBSUB_EVENT)
            .routeId("process-events")
            .bean(DeviceMetricHandler::class.java)
            .log(LoggingLevel.INFO, loggerName, "Successfully stored event: \${body}")
    }
}