package org.istheshit.turn.datacollector

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TurnDataCollectorApplication

fun main(args: Array<String>) {
    runApplication<TurnDataCollectorApplication>(*args)
}
