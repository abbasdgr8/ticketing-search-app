package com.examples.abbasdgr8.model.service

import com.examples.abbasdgr8.model.domain.User
import com.examples.abbasdgr8.model.service.exceptions.UserSearchError
import java.lang.Exception
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties

class UserSearchService : SearchService() {

    @Throws(UserSearchError::class)
    fun <T> findByField(fieldName: String, fieldValue: T): List<User> {
        val resultSet: List<User>
        val field: KProperty1<User, *>

        try {
            field = searchableFields.first { it.name == fieldName }
            resultSet = executeSearch(field, fieldValue)
        } catch (e: Exception) {
            throw UserSearchError(e)
        }

        return resultSet
    }

    fun getAllSearchableFieldNames(): List<String> {
        return super.getAllSearchableFieldNames(searchableFields)
    }

    private fun <T> executeSearch(field: KProperty1<User, *>, fieldValue: T): List<User> {
        return users.filter { field.get(it) == fieldValue }
    }

    companion object {
        private val searchableFields: Collection<KProperty1<User, *>>
            get() {
                return User::class.memberProperties
            }
    }
}
