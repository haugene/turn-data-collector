package org.istheshit.turn.datacollector.repositories

import org.istheshit.turn.datacollector.domain.Location
import org.springframework.data.repository.CrudRepository

interface LocationRepository : CrudRepository<Location, Long>