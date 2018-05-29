package org.istheshit.turn.datacollector.repositories

import org.istheshit.turn.datacollector.domain.Measurement
import org.springframework.data.repository.CrudRepository

interface MeasurementRepository : CrudRepository<Measurement, Long>