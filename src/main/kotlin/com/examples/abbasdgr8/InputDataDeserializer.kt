package com.examples.abbasdgr8

import com.examples.abbasdgr8.model.Ticket
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.File

class InputDataDeserializer {
    fun readTickets(jsonFile: File): List<Ticket> {

        val jsonString = jsonFile.readText()

        val gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss X")
                .create()

        val ticketsType = object : TypeToken<ArrayList<Ticket>>() {}.type

        val tickets = gson.fromJson<List<Ticket>>(jsonString, ticketsType)

        return tickets;
    }
}
