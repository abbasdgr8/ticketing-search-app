package com.examples.abbasdgr8.controller.state

sealed class UserEvent {

    object Proceed: UserEvent()
    object Exit: UserEvent()
    object Error: UserEvent()

    object ViewMainMenu: UserEvent()
    object ViewTicketsMenu: UserEvent()
    object ViewUsersMenu: UserEvent()
    object ViewOrgsMenu: UserEvent()

    object ViewSearchableFields: UserEvent()

    object InputSearchField: UserEvent()
    object InputSearchValue: UserEvent()

}

sealed class CliSideEffect {
    object LogAction: CliSideEffect()
}