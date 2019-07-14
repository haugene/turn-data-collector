package org.istheshit.turn.datacollector.routes

import org.apache.camel.LoggingLevel
import org.apache.camel.component.google.pubsub.GooglePubsubConstants
import org.apache.camel.spring.SpringRouteBuilder
import org.istheshit.turn.datacollector.beans.DeviceLocationHandler
import org.istheshit.turn.datacollector.beans.DeviceMetricHandler
import org.springframework.context.support.beans
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
            .choice()
            .`when` { (it.message.headers[GooglePubsubConstants.ATTRIBUTES] as Map<String, String>)["event"] == "location"}
                .bean(DeviceLocationHandler::class.java)
                .log(LoggingLevel.INFO, loggerName, "Successfully stored location: \${body}")
            .otherwise()
                .bean(DeviceMetricHandler::class.java)
                .log(LoggingLevel.INFO, loggerName, "Successfully stored metric event: \${body}")
            .end()
    }
}