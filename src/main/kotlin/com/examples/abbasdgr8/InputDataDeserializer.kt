package com.examples.abbasdgr8

import com.examples.abbasdgr8.exception.InputDataDeserializationException
import com.examples.abbasdgr8.model.Ticket
import com.examples.abbasdgr8.model.User
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.File
import java.lang.Exception

class InputDataDeserializer {

    private val gson = GsonBuilder()
                        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss X")
                        .create()

    @Throws(InputDataDeserializationException::class)
    fun readTickets(jsonFile: File): List<Ticket> {

        val tickets: List<Ticket>
        val ticketsType = object : TypeToken<ArrayList<Ticket>>() {}.type

        try {
            tickets = gson.fromJson<List<Ticket>>(jsonFile.readText(), ticketsType)
        } catch (e: Exception) {
            throw InputDataDeserializationException(e.message, e.cause)
        }

        return tickets;
    }

    @Throws(InputDataDeserializationException::class)
    fun readUsers(jsonFile: File): List<User> {

        val users: List<User>
        val usersType = object : TypeToken<ArrayList<User>>() {}.type

        try {
            users = gson.fromJson<List<User>>(jsonFile.readText(), usersType)
        } catch (e: Exception) {
            throw InputDataDeserializationException(e.message, e.cause)
        }

        return users;
    }

}
