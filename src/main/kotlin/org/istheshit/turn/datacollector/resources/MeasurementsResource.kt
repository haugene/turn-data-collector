package org.istheshit.turn.datacollector.resources

import org.istheshit.turn.datacollector.domain.MeasurementDto
import org.istheshit.turn.datacollector.services.MeasurementService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class MeasurementsResource @Autowired constructor(val measurementService: MeasurementService) {

    @RequestMapping("/measurements/{boat}/", method = [(RequestMethod.POST)])
    fun updateProject(@PathVariable("boat") boat: String,
                      @RequestBody measurement: MeasurementDto
    ) {
        measurementService.addMeasurement(measurement, boat)
    }

}