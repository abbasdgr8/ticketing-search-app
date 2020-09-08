package com.examples.abbasdgr8.model.service

import com.examples.abbasdgr8.model.data.InputDataDeserializer
import com.examples.abbasdgr8.model.domain.Ticket
import java.io.File
import kotlin.reflect.full.memberProperties

class TicketSearchService {

    private val ticketsRepository = InputDataDeserializer()
                                    .readTickets(File("src/main/resources/input-data/tickets.json"))

    fun <T> findByField(fieldName: String, fieldValue: T): List<Ticket> {
        val fields = Ticket::class.memberProperties
        val field = fields.first { it.name == fieldName }

        return ticketsRepository.filter { field.get(it) == fieldValue }
    }

}
