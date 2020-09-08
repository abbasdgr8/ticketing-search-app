package com.examples.abbasdgr8.model.service

import com.examples.abbasdgr8.model.domain.Organization
import com.examples.abbasdgr8.model.service.exceptions.OrganizationSearchError
import java.lang.Exception
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
        return organizations.filter { field.get(it) == fieldValue }
    }

    companion object {
        private val searchableFields: Collection<KProperty1<Organization, *>>
            get() {
                return Organization::class.memberProperties
            }
    }
}
