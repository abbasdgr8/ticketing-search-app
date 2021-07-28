package com.examples.abbasdgr8.model.service

import com.examples.abbasdgr8.model.domain.Organization
import com.examples.abbasdgr8.model.domain.Ticket
import com.examples.abbasdgr8.model.domain.User
import com.examples.abbasdgr8.model.service.exceptions.OrganizationSearchError
import com.examples.abbasdgr8.view.constants.Inputs.EMPTY
import java.lang.Exception
import java.util.*
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties

class OrganizationSearchService : SearchService() {

    @Throws(OrganizationSearchError::class)
    fun <T> findByField(fieldName: String, fieldValue: T): List<Organization> {
        val resultSet: List<Organization>
        val field: KProperty1<Organization, *>

        try {
            field = searchableFields.first { it.name == fieldName }
            resultSet = executeSearch(field, fieldValue)
        } catch (e: Exception) {
            throw OrganizationSearchError(e)
        }

        return resultSet
    }

    fun getAllSearchableFieldNames(): List<String> {
        return super.getAllSearchableFieldNames(searchableFields)
    }

    fun getAssociatedTicketsAndUsers(org: Organization): Pair<Set<Ticket>, Set<User>> {
        val associatedTickets = tickets.filter { ticket -> ticket.organization_id == org._id }.toMutableSet()
        val associatedUsers = users.filter { user -> user.organization_id == org._id }.toMutableSet()
        return Pair(associatedTickets, associatedUsers)
    }

    private fun <T> executeSearch(field: KProperty1<Organization, *>, fieldValue: T): List<Organization> {
        val searchText = fieldValue.toString()
        return when(field.returnType.toString()) {
            "kotlin.Int" -> executeIntegerSearch(field, if (searchText == EMPTY.s) Int.MIN_VALUE else searchText.toInt())
            "kotlin.String" -> executeTextSearch(field, searchText)
            "java.util.Date" -> executeDateSearch(field, Date())
            "kotlin.Boolean" -> executeBooleanSearch(field, if (searchText == EMPTY.s) true else searchText.toBoolean())
            else -> listOf()
        }
    }

    private fun executeTextSearch(field: KProperty1<Organization, *>, fieldValue: String): List<Organization> {
        return organizations.filter { field.get(it).toString().contains(fieldValue) }
    }

    private fun executeIntegerSearch(field: KProperty1<Organization, *>, fieldValue: Int): List<Organization> {
        return organizations.filter { field.get(it) == fieldValue }
    }

    private fun executeBooleanSearch(field: KProperty1<Organization, *>, fieldValue: Boolean): List<Organization> {
        return organizations.filter { field.get(it) == fieldValue }
    }

    private fun executeDateSearch(field: KProperty1<Organization, *>, date: Date): List<Organization> {
        TODO("Not yet implemented")
    }

    companion object {
        private val searchableFields: Collection<KProperty1<Organization, *>>
            get() {
                return Organization::class.memberProperties.filter {
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
