package org.istheshit.turn.datacollector.config

import org.apache.camel.component.google.pubsub.GooglePubsubConnectionFactory
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
@ConfigurationProperties(prefix = "camel.pubsub.auth")
class MessagingConfiguration {

    lateinit var serviceAccount: String
    lateinit var serviceAccountKey: String

    @Bean
    fun pubSubConnectionFactory(): GooglePubsubConnectionFactory {

        val connectionFactory = GooglePubsubConnectionFactory()
        connectionFactory.serviceAccount = serviceAccount
        connectionFactory.serviceAccountKey = serviceAccountKey
        return connectionFactory
    }
}