package com.examples.abbasdgr8.model.service

import com.examples.abbasdgr8.model.data.InputDataDeserializer
import com.examples.abbasdgr8.model.domain.Ticket
import java.io.File

class TicketSearchService {

    fun findById(id: String): Ticket? {

        val deserializer = InputDataDeserializer()
        val tickets = deserializer.readTickets(File("src/main/resources/input-data/tickets.json"))

        return tickets.singleOrNull { t -> t._id == id }

    }

}
