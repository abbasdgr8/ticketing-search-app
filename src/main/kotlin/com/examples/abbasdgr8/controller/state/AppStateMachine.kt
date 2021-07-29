package com.examples.abbasdgr8.controller.state

import com.tinder.StateMachine
import kotlin.system.exitProcess

class AppStateMachine {

    val stateMachine = StateMachine.create<CliState, UserEvent, CliSideEffect> {

        initialState(CliState.SplashScreen)

        state<CliState.SplashScreen> {
            on<UserEvent.Proceed> {
                transitionTo(CliState.MainMenu)
            }
        }

        state<CliState.MainMenu> {
            on<UserEvent.OptionOne> {
                transitionTo(CliState.OrgsMenu)
            }
            on<UserEvent.OptionTwo> {
                transitionTo(CliState.UsersMenu)
            }
            on<UserEvent.OptionThree> {
                transitionTo(CliState.TicketsMenu)
            }
            on<UserEvent.OptionFour> {
                transitionTo(CliState.End)
            }
        }

        state<CliState.TicketsMenu> {
            on<UserEvent.OptionOne> {
                transitionTo(CliState.TicketFields)
            }
            on<UserEvent.OptionTwo> {
                transitionTo(CliState.TicketSearchFieldInput)
            }
            on<UserEvent.OptionThree> {
                transitionTo(CliState.TicketAssocationsIdInput)
            }
            on<UserEvent.OptionFour> {
                transitionTo(CliState.MainMenu)
            }
        }

        state<CliState.UsersMenu> {
            on<UserEvent.OptionOne> {
                transitionTo(CliState.UserFields)
            }
            on<UserEvent.OptionTwo> {
                transitionTo(CliState.UserSearchFieldInput)
            }
            on<UserEvent.OptionThree> {
                transitionTo(CliState.UserAssocationsIdInput)
            }
            on<UserEvent.OptionFour> {
                transitionTo(CliState.MainMenu)
            }
        }

        state<CliState.OrgsMenu> {
            on<UserEvent.OptionOne> {
                transitionTo(CliState.OrgFields)
            }
            on<UserEvent.OptionTwo> {
                transitionTo(CliState.OrgSearchFieldInput)
            }
            on<UserEvent.OptionThree> {
                transitionTo(CliState.OrgAssocationsIdInput)
            }
            on<UserEvent.OptionFour> {
                transitionTo(CliState.MainMenu)
            }
        }

        state<CliState.TicketFields> {
            on<UserEvent.Proceed> {
                transitionTo(CliState.TicketsMenu)
            }
        }

        state<CliState.UserFields> {
            on<UserEvent.Proceed> {
                transitionTo(CliState.UsersMenu)
            }
        }

        state<CliState.OrgFields> {
            on<UserEvent.Proceed> {
                transitionTo(CliState.OrgsMenu)
            }
        }

        state<CliState.TicketSearchFieldInput> {
            on<UserEvent.InputSearchField> {
                transitionTo(CliState.TicketSearchValueInput)
            }
            on<UserEvent.Error> {
                transitionTo(CliState.TicketSearchFieldNameError)
            }
        }

        state<CliState.UserSearchFieldInput> {
            on<UserEvent.InputSearchField> {
                transitionTo(CliState.UserSearchValueInput)
            }
            on<UserEvent.Error> {
                transitionTo(CliState.UserSearchFieldNameError)
            }
        }

        state<CliState.OrgSearchFieldInput> {
            on<UserEvent.InputSearchField> {
                transitionTo(CliState.OrgSearchValueInput)
            }
            on<UserEvent.Error> {
                transitionTo(CliState.OrgSearchFieldNameError)
            }
        }

        state<CliState.TicketSearchValueInput> {
            on<UserEvent.InputSearchValue> {
                transitionTo(CliState.TicketsSearchResult)
            }
            on<UserEvent.Error> {
                transitionTo(CliState.TicketSearchFieldValueError)
            }
        }

        state<CliState.UserSearchValueInput> {
            on<UserEvent.InputSearchValue> {
                transitionTo(CliState.UsersSearchResult)
            }
            on<UserEvent.Error> {
                transitionTo(CliState.UserSearchFieldValueError)
            }
        }

        state<CliState.OrgSearchValueInput> {
            on<UserEvent.InputSearchValue> {
                transitionTo(CliState.OrgsSearchResult)
            }
            on<UserEvent.Error> {
                transitionTo(CliState.OrgSearchFieldValueError)
            }
        }

        state<CliState.TicketAssocationsIdInput> {
            on<UserEvent.InputSearchValue> {
                transitionTo(CliState.TicketAssociationsResult)
            }
            on<UserEvent.Error> {
                transitionTo(CliState.TicketAssocationsError)
            }
        }

        state<CliState.UserAssocationsIdInput> {
            on<UserEvent.InputSearchValue> {
                transitionTo(CliState.UserAssociationsResult)
            }
            on<UserEvent.Error> {
                transitionTo(CliState.UserAssocationsError)
            }
        }

        state<CliState.OrgAssocationsIdInput> {
            on<UserEvent.InputSearchValue> {
                transitionTo(CliState.OrgAssociationsResult)
            }
            on<UserEvent.Error> {
                transitionTo(CliState.OrgAssocationsError)
            }
        }

        state<CliState.TicketSearchFieldNameError> {
            on<UserEvent.Proceed> {
                transitionTo(CliState.TicketsMenu)
            }
        }

        state<CliState.UserSearchFieldNameError> {
            on<UserEvent.Proceed> {
                transitionTo(CliState.UsersMenu)
            }
        }

        state<CliState.OrgSearchFieldNameError> {
            on<UserEvent.Proceed> {
                transitionTo(CliState.OrgsMenu)
            }
        }

        state<CliState.TicketSearchFieldValueError> {
            on<UserEvent.Proceed> {
                transitionTo(CliState.TicketsMenu)
            }
        }

        state<CliState.UserSearchFieldValueError> {
            on<UserEvent.Proceed> {
                transitionTo(CliState.UsersMenu)
            }
        }

        state<CliState.OrgSearchFieldValueError> {
            on<UserEvent.Proceed> {
                transitionTo(CliState.OrgsMenu)
            }
        }

        state<CliState.TicketAssocationsError> {
            on<UserEvent.Proceed> {
                transitionTo(CliState.TicketsMenu)
            }
        }

        state<CliState.UserAssocationsError> {
            on<UserEvent.Proceed> {
                transitionTo(CliState.UsersMenu)
            }
        }

        state<CliState.OrgAssocationsError> {
            on<UserEvent.Proceed> {
                transitionTo(CliState.OrgsMenu)
            }
        }

        state<CliState.TicketsSearchResult> {
            on<UserEvent.Proceed> {
                transitionTo(CliState.TicketsMenu)
            }
        }

        state<CliState.UsersSearchResult> {
            on<UserEvent.Proceed> {
                transitionTo(CliState.UsersMenu)
            }
        }

        state<CliState.OrgsSearchResult> {
            on<UserEvent.Proceed> {
                transitionTo(CliState.OrgsMenu)
            }
        }

        state<CliState.TicketAssociationsResult> {
            on<UserEvent.Proceed> {
                transitionTo(CliState.TicketsMenu)
            }
        }

        state<CliState.UserAssociationsResult> {
            on<UserEvent.Proceed> {
                transitionTo(CliState.UsersMenu)
            }
        }

        state<CliState.OrgAssociationsResult> {
            on<UserEvent.Proceed> {
                transitionTo(CliState.OrgsMenu)
            }
        }

        state<CliState.End> {
            on<UserEvent.Proceed> {
                exitProcess(0)
            }
        }

    }
}