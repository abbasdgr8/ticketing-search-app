package com.examples.abbasdgr8.view

import com.examples.abbasdgr8.model.domain.Ticket
import java.lang.StringBuilder

class TicketsView {

    companion object {

        fun getTicketsMenu(): String {
            return ticketsMenu
        }

        fun getTicketsFields(fields: List<String>): String {
            val sb = StringBuilder()
            fields.forEach {field ->
                sb.append(System.lineSeparator())
                sb.append(field)
            }
            sb.append(System.lineSeparator())
            sb.append(System.lineSeparator())
            return ticketsFields + sb.toString()
        }

        fun getTicketRecord(tickets: List<Ticket>): String {
            val sb = StringBuilder()
            tickets.forEach { ticket ->
                sb.append(System.lineSeparator())
                sb.append(ticket.toString())
                sb.append(System.lineSeparator())
            }
            sb.append(System.lineSeparator())
            sb.append("Found ${tickets.size} results")
            sb.append(System.lineSeparator())

            return sb.toString()
        }

        private const val ticketsMenu = """                 
                                    |-------------------------------------------------------|
                                    |-------------------- TICKETS MENU ---------------------|
                                    |-------------------------------------------------------|
                                    | Select Search Options:                                |
                                    |                                                       |
                                    | Type 1 to view all Searchable fields on Tickets       |
                                    | Type 2 to search                                      |
                                    | Type 3 to go to back to the Main Menu                 |
                                    | ------------------------------------------------------|
                                    
                                    """

        private const val ticketsFields = """                 
                                    |-------------------------------------------------------|
                                    |------------------- TICKETS FIELDS --------------------|
                                    |-------------------------------------------------------|
                                    | Enter '.' followed by 'Enter' to Proceed              |
                                    
                                    """
    }

}