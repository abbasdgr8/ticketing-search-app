package com.examples.abbasdgr8.view

import com.examples.abbasdgr8.model.domain.Organization
import com.examples.abbasdgr8.model.domain.Ticket
import com.examples.abbasdgr8.model.domain.User
import com.examples.abbasdgr8.view.ViewCommons.Companion.getFieldsWithBanner
import com.examples.abbasdgr8.view.ViewCommons.Companion.getScreen
import com.examples.abbasdgr8.view.ViewCommons.Companion.getSearchResults

class TicketsView {

    companion object {

        fun getTicketsMenu(): String {
            return ticketsMenu
        }

        fun getTicketsFields(fields: List<String>): String {
            return getFieldsWithBanner(fields, ticketsFieldsBanner)
        }

        fun getSearchResults(tickets: List<Ticket>): String {
            return getSearchResults(tickets, "Ticket")
        }

        fun getAssociationResults(ticket: Ticket?, assocations: Pair<Organization?, Set<User>>): String {
            return getSearchResults(listOf(ticket), "Ticket") +
                    getSearchResults(listOf(assocations.first), "Organization", true) +
                    getSearchResults(assocations.second.toMutableList(), "User", true)
        }

        private val ticketsMenu = getScreen("tickets-menu")
        private val ticketsFieldsBanner = getScreen("tickets-fields")
    }
}