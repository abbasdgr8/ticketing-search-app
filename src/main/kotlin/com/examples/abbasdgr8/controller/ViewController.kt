package com.examples.abbasdgr8.controller

import com.examples.abbasdgr8.controller.state.CliState.*
import com.examples.abbasdgr8.view.IndexView
import com.examples.abbasdgr8.view.OrganizationsView
import com.examples.abbasdgr8.view.TicketsView
import com.examples.abbasdgr8.view.UsersView

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
            TicketSearchFieldInput -> return TicketsView.getTicketsFieldName()
            TicketSearchValueInput -> return TicketsView.getTicketsFieldValue()
            TicketRecord -> return TicketsView.getTicketRecord(ticketSearchService.findByField(cachedFieldName, cachedFieldValue))

            UsersMenu -> return UsersView.getUsersMenu()
            UserFields -> return UsersView.getUsersFields(userSearchService.getAllSearchableFieldNames())
            UserSearchFieldInput -> return UsersView.getUsersFieldName()
            UserSearchValueInput -> return UsersView.getUsersFieldValue()
            UserRecord -> return UsersView.getUserRecord(userSearchService.findByField(cachedFieldName, cachedFieldValue))

            OrgsMenu -> return OrganizationsView.getOrganizationsMenu()
            OrgFields -> return OrganizationsView.getOrganizationsFields(orgSearchService.getAllSearchableFieldNames())
            OrgSearchFieldInput -> return OrganizationsView.getOrganizationsFieldName()
            OrgSearchValueInput -> return OrganizationsView.getOrganizationsFieldValue()
            OrgRecord -> return OrganizationsView.getOrganizationRecord(orgSearchService.findByField(cachedFieldName, cachedFieldValue))
        }
    }

}