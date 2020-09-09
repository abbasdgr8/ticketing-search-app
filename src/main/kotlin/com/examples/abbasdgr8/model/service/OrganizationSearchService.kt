package com.examples.abbasdgr8.model.service

import com.examples.abbasdgr8.model.domain.Organization
import com.examples.abbasdgr8.model.service.exceptions.OrganizationSearchError
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

    private fun <T> executeSearch(field: KProperty1<Organization, *>, fieldValue: T): List<Organization> {
        return when(field.returnType.toString()) {
            "kotlin.Int" -> executeIntegerSearch(field, fieldValue.toString().toInt())
            "kotlin.String" -> executeTextSearch(field, fieldValue.toString())
            "java.util.Date" -> executeDateSearch(field, Date(fieldValue.toString()))
            else -> listOf()
        }
    }

    private fun executeTextSearch(field: KProperty1<Organization, *>, fieldValue: String): List<Organization> {
        return organizations.filter { field.get(it) == fieldValue }
    }

    private fun executeIntegerSearch(field: KProperty1<Organization, *>, fieldValue: Int): List<Organization> {
        return organizations.filter { field.get(it) == fieldValue }
    }

    private fun executeDateSearch(field: KProperty1<Organization, *>, date: Date): List<Organization> {
        TODO("Not yet implemented")
    }

    companion object {
        private val searchableFields: Collection<KProperty1<Organization, *>>
            get() {
                return Organization::class.memberProperties
            }
    }
}
