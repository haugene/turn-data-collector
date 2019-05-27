package org.istheshit.turn.datacollector.domain

class Device {

    companion object {
        fun findName(id: String): String {
            return when (id) {
                "4b0054001951343334363036" -> "turn"
                "190033000f47363336383437" -> "chargemeter"
                "21003e001947373239323130" -> "duo"
                else -> "UNKNOWN"
            }
        }
    }

}