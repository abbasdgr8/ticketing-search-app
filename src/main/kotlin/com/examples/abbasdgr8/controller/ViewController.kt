package com.examples.abbasdgr8.controller

import com.examples.abbasdgr8.controller.state.CliState.*
import com.examples.abbasdgr8.view.IndexView
import com.examples.abbasdgr8.view.TicketsView

class ViewController: SearchController() {

    fun process(userInput: String): String {
        modifyUserState(userInput)
        return view()
    }

    private fun view(): String {
        return when (stateMachine.state) {

            SplashScreen -> return IndexView.getSplashScreen()
            MainMenu -> return IndexView.getMainMenu()
            End -> IndexView.getEnd()

            TicketsMenu -> return TicketsView.getTicketsMenu()
            TicketFields -> return TicketsView.getTicketsFields(ticketSearchService.getAllSearchableFieldNames())
            TicketSearchFieldInput -> return TicketsView.getTicketsFieldName()
            TicketSearchValueInput -> return TicketsView.getTicketsFieldValue()
            TicketRecord -> return TicketsView.getTicketRecord(ticketSearchService.findByField(cachedFieldName, cachedFieldValue))

            UsersMenu -> return "Users Menu"
            UserFields -> return "User Fields"
            UserSearchFieldInput -> return "User Search Field Input"
            UserSearchValueInput -> return "User Search Value Input"
            UserRecord -> return "User Record"

            OrgsMenu -> return "Orgs Menu"
            OrgFields -> return "Org Fields"
            OrgSearchFieldInput -> return "Org Search Field Input"
            OrgSearchValueInput -> return "Org Search Value Input"
            OrgRecord -> return "Org Record"
        }
    }

}