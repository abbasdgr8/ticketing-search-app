package com.examples.abbasdgr8.controller.state

sealed class UserEvent {

    object Proceed: UserEvent()
    object Error: UserEvent()

    object OptionOne: UserEvent()
    object OptionTwo: UserEvent()
    object OptionThree: UserEvent()
    object OptionFour: UserEvent()

    object InputSearchField: UserEvent()
    object InputSearchValue: UserEvent()
}

sealed class CliSideEffect {
    object LogAction: CliSideEffect()
}