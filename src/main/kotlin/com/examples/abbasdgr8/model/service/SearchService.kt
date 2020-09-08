package com.examples.abbasdgr8.model.service

import com.examples.abbasdgr8.model.data.InMemoryDataRepository
import kotlin.reflect.KProperty1

abstract class SearchService {

    var tickets = InMemoryDataRepository.tickets
    var users = InMemoryDataRepository.users
    var organizations = InMemoryDataRepository.organizations

    fun <T> getAllSearchableFieldNames(fields: Collection<KProperty1<T, *>>): List<String> {
        return fields.mapTo(ArrayList()) { it.name }
    }

}