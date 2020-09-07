package com.examples.abbasdgr8

import com.examples.abbasdgr8.exception.InputDataDeserializationException
import com.examples.abbasdgr8.model.Organization
import com.examples.abbasdgr8.model.Ticket
import com.examples.abbasdgr8.model.User
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.File
import java.lang.Exception


class InputDataDeserializer {

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
                throw InputDataDeserializationException(e.message, e.cause)
            }

            return objects
        }

    }
}
