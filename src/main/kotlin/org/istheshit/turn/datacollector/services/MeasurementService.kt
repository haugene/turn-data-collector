package org.istheshit.turn.datacollector.services

import org.istheshit.turn.datacollector.domain.Measurement
import org.istheshit.turn.datacollector.domain.MeasurementDto
import org.istheshit.turn.datacollector.repositories.MeasurementRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MeasurementService @Autowired constructor(val measurementRepository: MeasurementRepository) {

    fun addMeasurement(measurementDto: MeasurementDto, boatName: String) {
        measurementRepository.save(Measurement(measurementDto, boatName))
    }

}