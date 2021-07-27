package com.examples.abbasdgr8.view

import com.examples.abbasdgr8.model.domain.Ticket
import com.examples.abbasdgr8.view.ViewCommons.Companion.getFieldsWithBanner
import com.examples.abbasdgr8.view.ViewCommons.Companion.getRecordsWithBanner
import com.examples.abbasdgr8.view.ViewCommons.Companion.getScreen

class TicketsView {

    companion object {

        fun getTicketsMenu(): String {
            return ticketsMenu
        }

        fun getTicketsFields(fields: List<String>): String {
            return getFieldsWithBanner(fields, ticketsFieldsBanner)
        }

        fun getTicketRecord(tickets: List<Ticket>): String {
            return getRecordsWithBanner(tickets)
        }

        private val ticketsMenu = getScreen("tickets-menu")
        private val ticketsFieldsBanner = getScreen("tickets-fields")
    }
}