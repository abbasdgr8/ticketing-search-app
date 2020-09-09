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
            on<UserEvent.ViewTicketsMenu> {
                transitionTo(CliState.TicketsMenu)
            }
            on<UserEvent.ViewUsersMenu> {
                transitionTo(CliState.UsersMenu)
            }
            on<UserEvent.ViewOrgsMenu> {
                transitionTo(CliState.OrgsMenu)
            }
            on<UserEvent.Exit> {
                transitionTo(CliState.End)
            }
        }

        state<CliState.TicketsMenu> {
            on<UserEvent.ViewSearchableFields> {
                transitionTo(CliState.TicketFields)
            }
            on<UserEvent.InputSearchField> {
                transitionTo(CliState.TicketSearchFieldInput)
            }
            on<UserEvent.ViewMainMenu> {
                transitionTo(CliState.MainMenu)
            }
        }

        state<CliState.UsersMenu> {
            on<UserEvent.ViewSearchableFields> {
                transitionTo(CliState.UserFields)
            }
            on<UserEvent.InputSearchField> {
                transitionTo(CliState.UserSearchFieldInput)
            }
            on<UserEvent.ViewMainMenu> {
                transitionTo(CliState.MainMenu)
            }
        }

        state<CliState.OrgsMenu> {
            on<UserEvent.ViewSearchableFields> {
                transitionTo(CliState.OrgFields)
            }
            on<UserEvent.InputSearchField> {
                transitionTo(CliState.OrgSearchFieldInput)
            }
            on<UserEvent.ViewMainMenu> {
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
            on<UserEvent.InputSearchValue> {
                transitionTo(CliState.TicketSearchValueInput)
            }
        }

        state<CliState.UserSearchFieldInput> {
            on<UserEvent.InputSearchValue> {
                transitionTo(CliState.UserSearchValueInput)
            }
        }

        state<CliState.OrgSearchFieldInput> {
            on<UserEvent.InputSearchValue> {
                transitionTo(CliState.OrgSearchValueInput)
            }
        }

        state<CliState.TicketSearchValueInput> {
            on<UserEvent.Proceed> {
                transitionTo(CliState.TicketRecord)
            }
        }

        state<CliState.UserSearchValueInput> {
            on<UserEvent.Proceed> {
                transitionTo(CliState.UserRecord)
            }
        }

        state<CliState.OrgSearchValueInput> {
            on<UserEvent.Proceed> {
                transitionTo(CliState.OrgRecord)
            }
        }

        state<CliState.TicketRecord> {
            on<UserEvent.Proceed> {
                transitionTo(CliState.TicketsMenu)
            }
        }

        state<CliState.UserRecord> {
            on<UserEvent.Proceed> {
                transitionTo(CliState.MainMenu)
            }
        }

        state<CliState.OrgRecord> {
            on<UserEvent.Proceed> {
                transitionTo(CliState.MainMenu)
            }
        }

        state<CliState.End> {
            on<UserEvent.Proceed> {
                exitProcess(0)
            }
        }

    }

}