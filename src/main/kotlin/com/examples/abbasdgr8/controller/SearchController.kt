package com.examples.abbasdgr8.controller

import com.examples.abbasdgr8.controller.state.AppStateMachine
import com.examples.abbasdgr8.controller.state.CliState.*
import com.examples.abbasdgr8.controller.state.UserEvent
import com.examples.abbasdgr8.model.service.OrganizationSearchService
import com.examples.abbasdgr8.model.service.TicketSearchService
import com.examples.abbasdgr8.model.service.UserSearchService
import kotlin.system.exitProcess

open class SearchController {

    protected val ticketSearchService: TicketSearchService
    protected val userSearchService: UserSearchService
    protected val orgSearchService: OrganizationSearchService

    protected val stateMachine = AppStateMachine().stateMachine

    protected var cachedFieldName = ""
    protected var cachedFieldValue = ""

    protected fun modifyUserState(input: String) {

        when (stateMachine.state) {

            SplashScreen -> {
                if (input.isNotEmpty()) {
                    stateMachine.transition(UserEvent.Proceed)
                }
            }

            MainMenu -> {
                when (input) {
                    "1" -> stateMachine.transition(UserEvent.ViewTicketsMenu)
                    "2" -> stateMachine.transition(UserEvent.ViewUsersMenu)
                    "3" -> stateMachine.transition(UserEvent.ViewOrgsMenu)
                    "4" -> stateMachine.transition(UserEvent.Exit)
                }
            }

            TicketsMenu, UsersMenu, OrgsMenu -> {
                when(input) {
                    "1" -> stateMachine.transition(UserEvent.ViewSearchableFields)
                    "2" -> stateMachine.transition(UserEvent.InputSearchField)
                    "3" -> stateMachine.transition(UserEvent.ViewMainMenu)
                }
            }

            TicketFields, UserFields, OrgFields -> {
                if (input.isNotEmpty()) {
                    stateMachine.transition(UserEvent.Proceed)
                }
            }

            TicketSearchFieldInput, UserSearchFieldInput, OrgSearchFieldInput -> {
                if (input.isNotEmpty()) {
                    cachedFieldName = input
                    stateMachine.transition(UserEvent.InputSearchValue)
                }
            }

            TicketSearchValueInput, UserSearchValueInput, OrgSearchValueInput -> {
                if (input.isNotEmpty()) {
                    cachedFieldValue = input
                    stateMachine.transition(UserEvent.Proceed)
                }
            }

            TicketRecord, UserRecord, OrgRecord -> {
                if (input.isNotEmpty()) {
                    stateMachine.transition(UserEvent.Proceed)
                }
            }

            End -> exitProcess(0)
        }
    }

    constructor() {
        this.ticketSearchService = TicketSearchService()
        this.userSearchService = UserSearchService()
        this.orgSearchService = OrganizationSearchService()
    }

    constructor(ticketSearchService: TicketSearchService,
                userSearchService: UserSearchService,
                orgSearchService: OrganizationSearchService) {
        this.ticketSearchService = ticketSearchService
        this.userSearchService = userSearchService
        this.orgSearchService = orgSearchService
    }
}