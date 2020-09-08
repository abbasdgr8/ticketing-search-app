package com.examples.abbasdgr8.model.service

import com.examples.abbasdgr8.model.data.InputDataDeserializer
import com.examples.abbasdgr8.model.domain.Ticket
import java.io.File
import java.lang.Exception
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties

class TicketSearchService {

    private val ticketsRepository = InputDataDeserializer()
                                    .readTickets(File("src/main/resources/input-data/tickets.json"))

    @Throws(TicketSearchException::class)
    fun <T> findByField(fieldName: String, fieldValue: T): List<Ticket> {
        val resultSet: List<Ticket>
        val field: KProperty1<Ticket, *>

        try {
            field = searchableFields.first { it.name == fieldName }
            resultSet = ticketsRepository.filter { field.get(it) == fieldValue }    // Decouple search logic from here
        } catch (e: Exception) {
            throw TicketSearchException(e)
        }

        return resultSet
    }

    fun getAllSearchableFieldNames(): List<String> {
        return searchableFields.mapTo(ArrayList()) { it.name }
    }


    companion object {
        private val searchableFields: Collection<KProperty1<Ticket, *>>
            get() {
                return Ticket::class.memberProperties
            }
    }
}
