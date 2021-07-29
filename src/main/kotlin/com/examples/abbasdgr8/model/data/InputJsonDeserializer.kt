package com.examples.abbasdgr8.model.data

import com.examples.abbasdgr8.model.domain.Organization
import com.examples.abbasdgr8.model.domain.Ticket
import com.examples.abbasdgr8.model.domain.User
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.File
import java.lang.Exception


class InputJsonDeserializer {

    fun readTickets(ticketsJsonFile: File): List<Ticket> = deserializeJson(ticketsJsonFile)
    fun readUsers(usersJsonFile: File): List<User> = deserializeJson(usersJsonFile)
    fun readOrganizations(orgsJsonFile: File): List<Organization> = deserializeJson(orgsJsonFile)

    companion object {

        private val gson: Gson = GsonBuilder()
                                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss X")
                                    .create()

        @Throws(InputDataDeserializationException::class)
        private inline fun <reified T> deserializeJson(jsonFile: File): ArrayList<T> {
            val objects: ArrayList<T>
            val type = TypeToken.getParameterized(ArrayList::class.java, T::class.java).type

            try {
                objects = gson.fromJson<ArrayList<T>>(jsonFile.readText(), type)
            } catch (e: Exception) {
                throw InputDataDeserializationException(e)
            }

            return objects
        }

    }
}
