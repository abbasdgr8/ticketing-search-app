package com.examples.abbasdgr8.model.service

import com.examples.abbasdgr8.model.domain.Organization
import com.examples.abbasdgr8.model.domain.Ticket
import com.examples.abbasdgr8.model.domain.User
import com.examples.abbasdgr8.model.service.exceptions.TicketSearchError
import com.examples.abbasdgr8.view.constants.Inputs.EMPTY
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

    fun getAssociatedOrgAndUsers(ticketId: String): Pair<Organization?, Set<User>> {
        val ticket = findById(ticketId)!!
        val associatedOrg = organizations.find { org -> org._id == ticket.organization_id }
        val associatedUsers = users.filter { user ->
            ticket.assignee_id == user._id ||
            ticket.submitter_id == user._id
        }.toMutableSet()
        return Pair(associatedOrg, associatedUsers)
    }

    @Throws(TicketSearchError::class)
    fun findById(id: String): Ticket? {
        val ticket: Ticket?
        try {
            ticket = tickets.findLast { t -> t._id == id }
        } catch (e: Exception) {
            throw TicketSearchError(e)
        }
        return ticket
    }

    private fun <T> executeSearch(field: KProperty1<Ticket, *>, fieldValue: T): List<Ticket> {
        val searchText = fieldValue.toString()
        return when(field.returnType.toString()) {
            "kotlin.Int" -> executeIntegerSearch(field, if (searchText == EMPTY.s) Int.MIN_VALUE else searchText.toInt())
            "kotlin.String" -> executeTextSearch(field, searchText)
            "java.util.Date" -> executeDateSearch(field, Date())
            "kotlin.Boolean" -> executeBooleanSearch(field, if (searchText == EMPTY.s) true else searchText.toBoolean())
            else -> listOf()
        }
    }

    private fun executeTextSearch(field: KProperty1<Ticket, *>, fieldValue: String): List<Ticket> {
        return tickets.filter { field.get(it).toString().contains(fieldValue) }
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
