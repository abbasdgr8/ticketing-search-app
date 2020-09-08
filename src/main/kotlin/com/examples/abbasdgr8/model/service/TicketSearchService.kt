package com.examples.abbasdgr8.model.service

import com.examples.abbasdgr8.model.domain.Ticket
import com.examples.abbasdgr8.model.service.exceptions.TicketSearchError
import java.lang.Exception
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties

class TicketSearchService : SearchService() {

    @Throws(TicketSearchError::class)
    fun <T> findByField(fieldName: String, fieldValue: T): List<Ticket> {
        val resultSet: List<Ticket>
        val field: KProperty1<Ticket, *>

        try {
            field = searchableFields.first { it.name == fieldName }
            resultSet = executeSearch(field, fieldValue)
        } catch (e: Exception) {
            throw TicketSearchError(e)
        }

        return resultSet
    }

    fun getAllSearchableFieldNames(): List<String> {
        return super.getAllSearchableFieldNames(searchableFields)
    }

    private fun <T> executeSearch(field: KProperty1<Ticket, *>, fieldValue: T): List<Ticket> {
        return tickets.filter { field.get(it) == fieldValue }
    }

    companion object {
        private val searchableFields: Collection<KProperty1<Ticket, *>>
            get() {
                return Ticket::class.memberProperties
            }
    }
}
