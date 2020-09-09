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
                sb.append("\n")
                sb.append(field)
            }
            return ticketsFields + sb.toString()
        }

        fun getTicketsFieldName(): String {
            return ticketsFieldName
        }

        fun getTicketsFieldValue(): String {
            return ticketsFieldValue
        }

        fun getTicketRecord(tickets: List<Ticket>): String {
            val sb = StringBuilder()
            tickets.forEach { ticket ->
                sb.append("\n")
                sb.append(ticket.toString())
                sb.append("\n")
            }
            sb.append("\n")
            sb.append("Found ${tickets.size} results")

            return sb.toString()
        }

        private const val ticketsMenu = """                 
                                    |-------------------------------------------------------|
                                    |-------------------- TICKETS MENU ---------------------|
                                    |-------------------------------------------------------|
                                    | Select Search Options:                                |
                                    |                                                       |
                                    | Press 1 to view all Searchable fields on Tickets      |
                                    | Press 2 to search                                     |
                                    | Press 3 to go to back to the Main Menu                |
                                    | ------------------------------------------------------|
                                    """

        private const val ticketsFields = """                 
                                    |-------------------------------------------------------|
                                    |------------------- TICKETS FIELDS --------------------|
                                    |-------------------------------------------------------|
                                    | Enter '.' followed by 'Enter' to Proceed              |
                                    """

        private const val ticketsFieldName = """                 
                                    Please enter name of the field that you want to search by:
                                    """

        private const val ticketsFieldValue = """                 
                                    Please enter value of the field that you want to search by:
                                    """
    }

}