package com.examples.abbasdgr8.controller.state

/**
 * This class represents the different view states that the
 * user experiences through their interaction with the CLI app
 */
sealed class CliState {

    object End: CliState()
    object SplashScreen: CliState()
    object MainMenu: CliState()

    object TicketsMenu: CliState()
    object TicketFields: CliState()
    object TicketSearchFieldInput: CliState()
    object TicketSearchValueInput: CliState()
    object TicketRecord: CliState()

    object UsersMenu: CliState()
    object UserFields: CliState()
    object UserSearchFieldInput: CliState()
    object UserSearchValueInput: CliState()
    object UserRecord: CliState()

    object OrgsMenu: CliState()
    object OrgFields: CliState()
    object OrgSearchFieldInput: CliState()
    object OrgSearchValueInput: CliState()
    object OrgRecord: CliState()

}