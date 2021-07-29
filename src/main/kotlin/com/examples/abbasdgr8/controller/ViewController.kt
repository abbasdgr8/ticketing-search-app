package com.examples.abbasdgr8.controller

import com.examples.abbasdgr8.controller.state.CliState.*
import com.examples.abbasdgr8.model.service.OrganizationSearchService
import com.examples.abbasdgr8.model.service.TicketSearchService
import com.examples.abbasdgr8.model.service.UserSearchService
import com.examples.abbasdgr8.model.domain.cli.Interaction
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
                TicketsSearchResult -> return Interaction(TicketsView.getSearchResults(ticketSearchService.findByField(searchFieldName, searchFieldValue)), showPrompt = false)
                TicketAssociationsResult -> return Interaction(TicketsView.getAssociationResults(ticketSearchService.findById(searchFieldValue), ticketSearchService.getAssociatedOrgAndUsers(searchFieldValue)), showPrompt = false)

                UsersMenu -> return Interaction(UsersView.getUsersMenu(), Prompts.CHOOSE.text, showDefault = false)
                UserFields -> return Interaction(UsersView.getUsersFields(userSearchService.getAllSearchableFieldNames()), showPrompt = false)
                UsersSearchResult -> return Interaction(UsersView.getSearchResults(userSearchService.findByField(searchFieldName, searchFieldValue)), showPrompt = false)
                UserAssociationsResult -> return Interaction(UsersView.getAssociationResults(userSearchService.findById(searchFieldValue), userSearchService.getAssociatedOrg(searchFieldValue)), showPrompt = false)

                OrgsMenu -> return Interaction(OrganizationsView.getOrganizationsMenu(), Prompts.CHOOSE.text, showDefault = false)
                OrgFields -> return Interaction(OrganizationsView.getOrganizationsFields(orgSearchService.getAllSearchableFieldNames()), showPrompt = false)
                OrgsSearchResult -> return Interaction(OrganizationsView.getSearchResults(orgSearchService.findByField(searchFieldName, searchFieldValue)), showPrompt = false)
                OrgAssociationsResult -> return Interaction(OrganizationsView.getAssociationResults(orgSearchService.findById(searchFieldValue), orgSearchService.getAssociatedTicketsAndUsers(searchFieldValue)), showPrompt = false)

                TicketSearchFieldInput, UserSearchFieldInput, OrgSearchFieldInput -> return Interaction(prompt = Prompts.FIELD_NAME.text, showDefault = false)
                TicketSearchValueInput, UserSearchValueInput, OrgSearchValueInput -> return Interaction(prompt = Prompts.FIELD_VALUE.text, default = EMPTY.s)
                TicketAssocationsIdInput, UserAssocationsIdInput, OrgAssocationsIdInput -> return Interaction(prompt = Prompts.ID_VALUE.text, default = EMPTY.s)

                TicketSearchFieldNameError, UserSearchFieldNameError, OrgSearchFieldNameError -> return Interaction(ViewCommons.getFieldNameErrorMsg(), Prompts.PREVIOUS.text)
                TicketSearchFieldValueError, UserSearchFieldValueError, OrgSearchFieldValueError -> return Interaction(ViewCommons.getFieldValueErrorMsg(), Prompts.PREVIOUS.text)
                TicketAssocationsError, UserAssocationsError, OrgAssocationsError -> return Interaction(ViewCommons.getAssocationsErrorMsg(), Prompts.PREVIOUS.text)
            }
        } catch (e: Exception) {
            return Interaction(ViewCommons.getFieldValueErrorMsg(), Prompts.PREVIOUS.text)
        }
    }

    constructor() : this(TicketSearchService(), UserSearchService(), OrganizationSearchService())
}