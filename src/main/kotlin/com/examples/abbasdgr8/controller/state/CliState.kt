package com.examples.abbasdgr8.controller.state

/**
 * This class represents the different states that the user
 * experiences throughout their interaction with this app
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
    object TicketsSearchResult: CliState()
    object TicketAssocationsIdInput: CliState()
    object TicketAssocationsError: CliState()
    object TicketAssociationsResult: CliState()

    object UsersMenu: CliState()
    object UserFields: CliState()
    object UserSearchFieldInput: CliState()
    object UserSearchFieldNameError: CliState()
    object UserSearchValueInput: CliState()
    object UserSearchFieldValueError: CliState()
    object UsersSearchResult: CliState()
    object UserAssocationsIdInput: CliState()
    object UserAssocationsError: CliState()
    object UserAssociationsResult: CliState()

    object OrgsMenu: CliState()
    object OrgFields: CliState()
    object OrgSearchFieldInput: CliState()
    object OrgSearchFieldNameError: CliState()
    object OrgSearchValueInput: CliState()
    object OrgSearchFieldValueError: CliState()
    object OrgsSearchResult: CliState()
    object OrgAssocationsIdInput: CliState()
    object OrgAssocationsError: CliState()
    object OrgAssociationsResult: CliState()
}