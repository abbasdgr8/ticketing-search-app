package com.examples.abbasdgr8

import com.examples.abbasdgr8.exception.InputDataDeserializationException
import com.examples.abbasdgr8.model.Ticket
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.File
import java.lang.Exception

class InputDataDeserializer {

    @Throws(InputDataDeserializationException::class)
    fun readTickets(jsonFile: File): List<Ticket> {

        val tickets: List<Ticket>

        val jsonString = jsonFile.readText()

        val gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss X")
                .create()

        val ticketsType = object : TypeToken<ArrayList<Ticket>>() {}.type

        try {
            tickets = gson.fromJson<List<Ticket>>(jsonString, ticketsType)
        } catch (e: Exception) {
            throw InputDataDeserializationException(e.message, e.cause)
        }

        return tickets;
    }

}
