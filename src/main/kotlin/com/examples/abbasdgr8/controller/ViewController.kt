package com.examples.abbasdgr8.controller

import com.examples.abbasdgr8.controller.state.CliState.*
import com.examples.abbasdgr8.model.service.OrganizationSearchService
import com.examples.abbasdgr8.model.service.TicketSearchService
import com.examples.abbasdgr8.model.service.UserSearchService
import com.examples.abbasdgr8.model.cli.Interaction
import com.examples.abbasdgr8.view.*
import com.examples.abbasdgr8.view.constants.Inputs.EMPTY
import com.examples.abbasdgr8.view.constants.Prompts
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
                TicketSearchFieldNameError -> return Interaction(ViewCommons.getFieldNameErrorMsg(), Prompts.PREVIOUS.text)
                TicketSearchFieldValueError -> return Interaction(ViewCommons.getFieldValueErrorMsg(), Prompts.PREVIOUS.text)

                UsersMenu -> return Interaction(UsersView.getUsersMenu(), Prompts.CHOOSE.text, showDefault = false)
                UserFields -> return Interaction(UsersView.getUsersFields(userSearchService.getAllSearchableFieldNames()), showPrompt = false)
                UserRecord -> return Interaction(UsersView.getUserRecord(userSearchService.findByField(searchFieldName, searchFieldValue)), showPrompt = false)
                UserSearchFieldNameError -> return Interaction(ViewCommons.getFieldNameErrorMsg(), Prompts.PREVIOUS.text)
                UserSearchFieldValueError -> return Interaction(ViewCommons.getFieldValueErrorMsg(), Prompts.PREVIOUS.text)

                OrgsMenu -> return Interaction(OrganizationsView.getOrganizationsMenu(), Prompts.CHOOSE.text, showDefault = false)
                OrgFields -> return Interaction(OrganizationsView.getOrganizationsFields(orgSearchService.getAllSearchableFieldNames()), showPrompt = false)
                OrgRecord -> return Interaction(OrganizationsView.getOrganizationRecord(orgSearchService.findByField(searchFieldName, searchFieldValue)), showPrompt = false)
                OrgSearchFieldNameError -> return Interaction(ViewCommons.getFieldNameErrorMsg(), Prompts.PREVIOUS.text)
                OrgSearchFieldValueError -> return Interaction(ViewCommons.getFieldValueErrorMsg(), Prompts.PREVIOUS.text)

                TicketSearchFieldInput, UserSearchFieldInput, OrgSearchFieldInput -> return Interaction(prompt = Prompts.FIELD_NAME.text, showDefault = false)
                TicketSearchValueInput, UserSearchValueInput, OrgSearchValueInput -> return Interaction(prompt = Prompts.FIELD_VALUE.text, default = EMPTY.s)
            }
        } catch (e: Exception) {
            return Interaction(ViewCommons.getFieldValueErrorMsg(), Prompts.PREVIOUS.text)
        }
    }

    constructor() : this(TicketSearchService(), UserSearchService(), OrganizationSearchService())
}