package com.examples.abbasdgr8.controller.state

/**
 * This class represents the different view states that the
 * user experiences through their interaction with the CLI app
 */
sealed class CliState {

    object SplashScreen: CliState()
    object MainMenu: CliState()
    object End: CliState()

    object TicketsMenu: CliState()
    object TicketFields: CliState()
    object TicketSearchFieldInput: CliState()
    object TicketSearchFieldNameError: CliState()
    object TicketSearchValueInput: CliState()
    object TicketSearchFieldValueError: CliState()
    object TicketRecord: CliState()

    object UsersMenu: CliState()
    object UserFields: CliState()
    object UserSearchFieldInput: CliState()
    object UserSearchFieldNameError: CliState()
    object UserSearchValueInput: CliState()
    object UserSearchFieldValueError: CliState()
    object UserRecord: CliState()

    object OrgsMenu: CliState()
    object OrgFields: CliState()
    object OrgSearchFieldInput: CliState()
    object OrgSearchFieldNameError: CliState()
    object OrgSearchValueInput: CliState()
    object OrgSearchFieldValueError: CliState()
    object OrgRecord: CliState()
}