package com.examples.abbasdgr8.model.service

import com.examples.abbasdgr8.model.domain.User
import com.examples.abbasdgr8.model.service.exceptions.UserSearchError
import com.examples.abbasdgr8.view.constants.Inputs
import java.lang.Exception
import java.util.*
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
        val searchText = fieldValue.toString()
        return when(field.returnType.toString()) {
            "kotlin.Int" -> executeIntegerSearch(field, if (searchText == Inputs.EMPTY.s) Int.MIN_VALUE else searchText.toInt())
            "kotlin.String" -> executeTextSearch(field, searchText)
            "java.util.Date" -> executeDateSearch(field, Date())
            "kotlin.Boolean" -> executeBooleanSearch(field, if (searchText == Inputs.EMPTY.s) true else searchText.toBoolean())
            else -> listOf()
        }
    }

    private fun executeTextSearch(field: KProperty1<User, *>, fieldValue: String): List<User> {
        return users.filter { field.get(it).toString().contains(fieldValue) }
    }

    private fun executeIntegerSearch(field: KProperty1<User, *>, fieldValue: Int): List<User> {
        return users.filter { field.get(it) == fieldValue }
    }

    private fun executeBooleanSearch(field: KProperty1<User, *>, fieldValue: Boolean): List<User> {
        return users.filter { field.get(it) == fieldValue }
    }

    private fun executeDateSearch(field: KProperty1<User, *>, date: Date): List<User> {
        TODO("Not yet implemented")
    }

    companion object {
        private val searchableFields: Collection<KProperty1<User, *>>
            get() {
                return User::class.memberProperties.filter {
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
