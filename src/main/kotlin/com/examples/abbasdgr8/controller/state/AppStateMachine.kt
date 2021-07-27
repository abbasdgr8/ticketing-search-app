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
                transitionTo(CliState.TicketsMenu)
            }
            on<UserEvent.OptionTwo> {
                transitionTo(CliState.UsersMenu)
            }
            on<UserEvent.OptionThree> {
                transitionTo(CliState.OrgsMenu)
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
                transitionTo(CliState.TicketRecord)
            }
            on<UserEvent.Error> {
                transitionTo(CliState.TicketSearchFieldValueError)
            }
        }

        state<CliState.UserSearchValueInput> {
            on<UserEvent.InputSearchValue> {
                transitionTo(CliState.UserRecord)
            }
            on<UserEvent.Error> {
                transitionTo(CliState.UserSearchFieldValueError)
            }
        }

        state<CliState.OrgSearchValueInput> {
            on<UserEvent.InputSearchValue> {
                transitionTo(CliState.OrgRecord)
            }
            on<UserEvent.Error> {
                transitionTo(CliState.OrgSearchFieldValueError)
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

        state<CliState.TicketRecord> {
            on<UserEvent.Proceed> {
                transitionTo(CliState.TicketsMenu)
            }
        }

        state<CliState.UserRecord> {
            on<UserEvent.Proceed> {
                transitionTo(CliState.UsersMenu)
            }
        }

        state<CliState.OrgRecord> {
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