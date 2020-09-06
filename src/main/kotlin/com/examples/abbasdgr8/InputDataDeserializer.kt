package com.examples.abbasdgr8

import com.examples.abbasdgr8.model.Ticket
import java.io.File

class InputDataDeserializer {
    fun readTickets(jsonFile: File): List<Ticket> {
        val tickets = ArrayList<Ticket>();
        val ticket = Ticket();
        tickets.add(ticket);

        return tickets;
    }
}
