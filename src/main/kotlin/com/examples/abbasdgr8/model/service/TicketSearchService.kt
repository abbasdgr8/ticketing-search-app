package com.examples.abbasdgr8.model.service

import com.examples.abbasdgr8.model.data.InputDataDeserializer
import com.examples.abbasdgr8.model.domain.Ticket
import java.io.File

class TicketSearchService {

    private val ticketsRepository = InputDataDeserializer()
                                    .readTickets(File("src/main/resources/input-data/tickets.json"))

    fun findById(id: String): Ticket? {
        return ticketsRepository.singleOrNull { t -> t._id == id }
    }

    fun findByAssigneeId(assigneeId: Int): List<Ticket> {
        return ticketsRepository.filter { ticket -> ticket.assignee_id == assigneeId }
    }

}
