package com.examples.abbasdgr8.model.service

import com.examples.abbasdgr8.model.domain.Ticket
import com.examples.abbasdgr8.model.service.exceptions.TicketSearchError
import java.lang.Exception
import java.util.*
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
        return when(field.returnType.toString()) {
            "kotlin.Int" -> executeIntegerSearch(field, fieldValue.toString().toInt())
            "kotlin.String" -> executeTextSearch(field, fieldValue.toString())
            "java.util.Date" -> executeDateSearch(field, Date())
            "kotlin.Boolean" -> executeBooleanSearch(field, fieldValue.toString().toBoolean())
            else -> listOf()
        }
    }

    private fun executeTextSearch(field: KProperty1<Ticket, *>, fieldValue: String): List<Ticket> {
        return tickets.filter { field.get(it) == fieldValue }
    }

    private fun executeIntegerSearch(field: KProperty1<Ticket, *>, fieldValue: Int): List<Ticket> {
        return tickets.filter { field.get(it) == fieldValue }
    }

    private fun executeBooleanSearch(field: KProperty1<Ticket, *>, fieldValue: Boolean): List<Ticket> {
        return tickets.filter { field.get(it) == fieldValue }
    }

    private fun executeDateSearch(field: KProperty1<Ticket, *>, date: Date): List<Ticket> {
        TODO("Not yet implemented")
    }

    companion object {
        private val searchableFields: Collection<KProperty1<Ticket, *>>
            get() {
                return Ticket::class.memberProperties.filter {
                    when(it.returnType.toString()) {
                        // Exclude fields with these types since search on them is not yet supported
                        "java.util.Date" -> false
                        "kotlin.collections.List<kotlin.String>" -> false
                        else -> true
                    }
                }
            }
    }
}
