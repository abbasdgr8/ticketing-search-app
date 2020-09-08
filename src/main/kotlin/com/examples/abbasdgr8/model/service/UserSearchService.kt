package com.examples.abbasdgr8.model.service

import com.examples.abbasdgr8.model.data.InputDataDeserializer
import com.examples.abbasdgr8.model.domain.User
import java.io.File
import java.lang.Exception
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties

class UserSearchService {

    private val usersRepository = InputDataDeserializer()
                                    .readUsers(File("src/main/resources/input-data/users.json"))

    @Throws(UserSearchError::class)
    fun <T> findByField(fieldName: String, fieldValue: T): List<User> {
        val resultSet: List<User>
        val field: KProperty1<User, *>

        try {
            field = searchableFields.first { it.name == fieldName }
            resultSet = usersRepository.filter { field.get(it) == fieldValue }    // Decouple search logic from here
        } catch (e: Exception) {
            throw UserSearchError(e)
        }

        return resultSet
    }

    fun getAllSearchableFieldNames(): List<String> {
        return searchableFields.mapTo(ArrayList()) { it.name }
    }


    companion object {
        private val searchableFields: Collection<KProperty1<User, *>>
            get() {
                return User::class.memberProperties
            }
    }
}
