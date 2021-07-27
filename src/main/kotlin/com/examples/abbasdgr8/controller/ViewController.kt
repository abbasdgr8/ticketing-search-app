package com.examples.abbasdgr8.controller

import com.examples.abbasdgr8.controller.state.CliState.*
import com.examples.abbasdgr8.model.service.OrganizationSearchService
import com.examples.abbasdgr8.model.service.TicketSearchService
import com.examples.abbasdgr8.model.service.UserSearchService
import com.examples.abbasdgr8.view.*
import java.lang.Exception


class ViewController(ticketSearchService: TicketSearchService,
                     userSearchService: UserSearchService,
                     orgSearchService: OrganizationSearchService)
        : Controller(ticketSearchService, userSearchService, orgSearchService) {

    fun getInteraction(userInput: String): Interaction {
        transitionState(userInput)
        return generateInteraction()
    }

    private fun generateInteraction(): Interaction {
        try {
            when (stateMachine.state) {

                SplashScreen -> return Interaction(IndexView.getSplashScreen(), Prompts.ENTER.text)
                MainMenu -> return Interaction(IndexView.getMainMenu(), Prompts.CHOOSE.text, showDefault = false)
                End -> return Interaction(IndexView.getEnd(), showPrompt = false)

                TicketsMenu -> return Interaction(TicketsView.getTicketsMenu(), Prompts.CHOOSE.text, showDefault = false)
                TicketFields -> return Interaction(TicketsView.getTicketsFields(ticketSearchService.getAllSearchableFieldNames()), showPrompt = false)
                TicketRecord -> return Interaction(TicketsView.getTicketRecord(ticketSearchService.findByField(searchFieldName, searchFieldValue)), showPrompt = false)
                TicketSearchFieldNameError -> return Interaction(ViewCommons.getFieldNameErrorMsg(), Prompts.BACK.text)
                TicketSearchFieldValueError -> return Interaction(ViewCommons.getFieldValueErrorMsg(), Prompts.BACK.text)

                UsersMenu -> return Interaction(UsersView.getUsersMenu(), Prompts.CHOOSE.text, showDefault = false)
                UserFields -> return Interaction(UsersView.getUsersFields(userSearchService.getAllSearchableFieldNames()), showPrompt = false)
                UserRecord -> return Interaction(UsersView.getUserRecord(userSearchService.findByField(searchFieldName, searchFieldValue)), showPrompt = false)
                UserSearchFieldNameError -> return Interaction(ViewCommons.getFieldNameErrorMsg(), Prompts.BACK.text)
                UserSearchFieldValueError -> return Interaction(ViewCommons.getFieldValueErrorMsg(), Prompts.BACK.text)

                OrgsMenu -> return Interaction(OrganizationsView.getOrganizationsMenu(), Prompts.CHOOSE.text, showDefault = false)
                OrgFields -> return Interaction(OrganizationsView.getOrganizationsFields(orgSearchService.getAllSearchableFieldNames()), showPrompt = false)
                OrgRecord -> return Interaction(OrganizationsView.getOrganizationRecord(orgSearchService.findByField(searchFieldName, searchFieldValue)), showPrompt = false)
                OrgSearchFieldNameError -> return Interaction(ViewCommons.getFieldNameErrorMsg(), Prompts.BACK.text)
                OrgSearchFieldValueError -> return Interaction(ViewCommons.getFieldValueErrorMsg(), Prompts.BACK.text)

                TicketSearchFieldInput, UserSearchFieldInput, OrgSearchFieldInput -> return Interaction(prompt = ViewCommons.getFieldNamePrompt(), showDefault = false)
                TicketSearchValueInput, UserSearchValueInput, OrgSearchValueInput -> return Interaction(prompt = ViewCommons.getFieldValuePrompt(), showDefault = false)
            }
        } catch (e: Exception) {
            return Interaction(ViewCommons.getFieldValueErrorMsg(), Prompts.BACK.text)
        }
    }

    constructor() : this(TicketSearchService(), UserSearchService(), OrganizationSearchService())
}