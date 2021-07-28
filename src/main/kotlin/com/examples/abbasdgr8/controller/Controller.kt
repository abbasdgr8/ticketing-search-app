package com.examples.abbasdgr8.controller

import com.examples.abbasdgr8.controller.state.AppStateMachine
import com.examples.abbasdgr8.controller.state.CliState.*
import com.examples.abbasdgr8.controller.state.UserEvent
import com.examples.abbasdgr8.model.service.OrganizationSearchService
import com.examples.abbasdgr8.model.service.TicketSearchService
import com.examples.abbasdgr8.model.service.UserSearchService
import kotlin.system.exitProcess


open class Controller(protected val ticketSearchService: TicketSearchService,
                      protected val userSearchService: UserSearchService,
                      protected val orgSearchService: OrganizationSearchService) {

    protected var stateMachine = AppStateMachine().stateMachine
    protected var searchFieldName = ""
    protected var searchFieldValue = ""

    /**
     * All the hairy logic around what input maps to which UserEvent goes here
     */
    protected fun transitionState(input: String) {

        when (stateMachine.state) {
            SplashScreen -> if (input.isNotBlank()) stateMachine.transition(UserEvent.Proceed)
            MainMenu, TicketsMenu, UsersMenu, OrgsMenu -> doMenuMapping(input)
            TicketFields, UserFields, OrgFields -> stateMachine.transition(UserEvent.Proceed)
            TicketSearchFieldInput, UserSearchFieldInput, OrgSearchFieldInput -> {
                if (isValidInputSearchField(input)) {
                    searchFieldName = input
                    stateMachine.transition(UserEvent.InputSearchField)
                } else {
                    stateMachine.transition(UserEvent.Error)
                }
            }
            TicketSearchValueInput, UserSearchValueInput, OrgSearchValueInput -> {
                searchFieldValue = input
                stateMachine.transition(UserEvent.InputSearchValue)
            }
            UserAssocationsIdInput -> {
                if (isRecordWithIdPresent(input)) {
                    searchFieldValue = input
                    stateMachine.transition(UserEvent.InputSearchValue)
                } else {
                    stateMachine.transition(UserEvent.Error)
                }
            }
            TicketsSearchResult, UsersSearchResult, OrgsSearchResult,
            UserAssociationsResult -> stateMachine.transition(UserEvent.Proceed)
            TicketSearchFieldNameError, UserSearchFieldNameError, OrgSearchFieldNameError -> stateMachine.transition(UserEvent.Proceed)
            TicketSearchFieldValueError, UserSearchFieldValueError, OrgSearchFieldValueError,
            UserAssocationsError -> stateMachine.transition(UserEvent.Proceed)
            End -> exitProcess(0)
        }
    }

    private fun isRecordWithIdPresent(input: String): Boolean {
        return when (stateMachine.state) {
            UserAssocationsIdInput -> userSearchService.findById(input) != null
            else -> false
        }
    }

    private fun isValidInputSearchField(input: String): Boolean {
        return when (stateMachine.state) {
            TicketSearchFieldInput -> ticketSearchService.getAllSearchableFieldNames().contains(input)
            UserSearchFieldInput -> userSearchService.getAllSearchableFieldNames().contains(input)
            OrgSearchFieldInput -> orgSearchService.getAllSearchableFieldNames().contains(input)
            else -> false
        }
    }

    private fun doMenuMapping(input: String) {
        when (input) {
            "" -> stateMachine.transition(UserEvent.Proceed)
            "1" -> stateMachine.transition(UserEvent.OptionOne)
            "2" -> stateMachine.transition(UserEvent.OptionTwo)
            "3" -> stateMachine.transition(UserEvent.OptionThree)
            "4" -> stateMachine.transition(UserEvent.OptionFour)
            else -> stateMachine.transition(UserEvent.Error)
        }
    }

}