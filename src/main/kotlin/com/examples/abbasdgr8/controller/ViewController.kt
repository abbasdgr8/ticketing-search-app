package com.examples.abbasdgr8.controller

import com.examples.abbasdgr8.controller.state.CliState.*
import com.examples.abbasdgr8.view.*

class ViewController: SearchController() {

    fun processAction(userInput: String): String {
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
            TicketRecord -> return TicketsView.getTicketRecord(ticketSearchService.findByField(cachedFieldName, cachedFieldValue))

            UsersMenu -> return UsersView.getUsersMenu()
            UserFields -> return UsersView.getUsersFields(userSearchService.getAllSearchableFieldNames())
            UserRecord -> return UsersView.getUserRecord(userSearchService.findByField(cachedFieldName, cachedFieldValue))

            OrgsMenu -> return OrganizationsView.getOrganizationsMenu()
            OrgFields -> return OrganizationsView.getOrganizationsFields(orgSearchService.getAllSearchableFieldNames())
            OrgRecord -> return OrganizationsView.getOrganizationRecord(orgSearchService.findByField(cachedFieldName, cachedFieldValue))

            TicketSearchFieldInput, UserSearchFieldInput, OrgSearchFieldInput -> return ViewCommons.getFieldNamePrompt()
            TicketSearchValueInput, UserSearchValueInput, OrgSearchValueInput -> return ViewCommons.getFieldValuePrompt()
        }
    }

}