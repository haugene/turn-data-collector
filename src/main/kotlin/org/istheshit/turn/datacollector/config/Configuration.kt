package org.istheshit.turn.datacollector.config

import java.lang.System.getenv

object Configuration {

    // TODO: Externalize config, read from env. This is just an example
    val HOSTNAME: String = getProperty("HOSTNAME")

    fun getProperty(key: String): String {
        if (getenv(key).isNullOrBlank())
            throw IllegalArgumentException("Required parameter $key is not set in environment")
        else
            return getenv(key)
    }

}
