package com.examples.abbasdgr8

import com.examples.abbasdgr8.model.Ticket
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

class InputDataDeserializer {
    fun readTickets(jsonFile: File): List<Ticket> {
        val tickets = ArrayList<Ticket>();
        val ticket = Ticket(
                "",
                "",
                "",
                Date(),
                "",
                "",
                "",
                "",
                "",
                1,
                1,
                1,
                ArrayList<String>(),
                false,
                Date(),
                "");
        tickets.add(ticket);

        return tickets;
    }
}
