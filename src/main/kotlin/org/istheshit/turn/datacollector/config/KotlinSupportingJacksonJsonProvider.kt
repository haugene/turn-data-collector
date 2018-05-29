package org.istheshit.turn.datacollector.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider

class KotlinSupportingJacksonJsonProvider : JacksonJsonProvider(ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .registerKotlinModule())