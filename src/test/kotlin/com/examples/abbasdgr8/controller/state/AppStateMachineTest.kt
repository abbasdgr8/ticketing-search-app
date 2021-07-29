package com.examples.abbasdgr8.controller.state

import com.tinder.StateMachine
import org.amshove.kluent.shouldBeEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

class AppStateMachineTest: Spek({

    Feature("App State Machine works as expected") {

        Scenario("StateMachine initializes at State=Splash Screen") {

            lateinit var stateMachine: StateMachine<CliState, UserEvent, CliSideEffect>

            Given("An initialized state machine") {
                stateMachine = AppStateMachine().stateMachine
            }

            When("On doing nothing") { }

            Then("") {
                stateMachine.state shouldBeEqualTo CliState.SplashScreen
            }
        }

        Scenario("StateMachine transitions to Main Menu on Proceed") {

            lateinit var stateMachine: StateMachine<CliState, UserEvent, CliSideEffect>

            Given("Current State = Splash Screen") {
                stateMachine = AppStateMachine().stateMachine.with { initialState(CliState.SplashScreen) }
            }

            When("On UserEvent Proceed") {
                stateMachine.transition(UserEvent.Proceed)
            }

            Then("Transitions to the MainMenu") {
                stateMachine.state shouldBeEqualTo CliState.MainMenu
            }
        }


        Scenario("StateMachine transitions from Main Menu to Tickets Menu") {

            lateinit var stateMachine: StateMachine<CliState, UserEvent, CliSideEffect>

            Given("Current State = Main Menu") {
                stateMachine = AppStateMachine().stateMachine.with { initialState(CliState.MainMenu) }
            }

            When("On UserEvent ViewTicketsMenu") {
                stateMachine.transition(UserEvent.OptionOne)
            }

            Then("Transitions to the TicketsMenu") {
                stateMachine.state shouldBeEqualTo CliState.OrgsMenu
            }
        }


        Scenario("StateMachine transitions from Main Menu to Users Menu") {

            lateinit var stateMachine: StateMachine<CliState, UserEvent, CliSideEffect>

            Given("Current State = Main Menu") {
                stateMachine = AppStateMachine().stateMachine.with { initialState(CliState.MainMenu) }
            }

            When("On UserEvent ViewUsersMenu") {
                stateMachine.transition(UserEvent.OptionTwo)
            }

            Then("Transitions to the UsersMenu") {
                stateMachine.state shouldBeEqualTo CliState.UsersMenu
            }
        }


        Scenario("StateMachine transitions from Main Menu to Orgs Menu") {

            lateinit var stateMachine: StateMachine<CliState, UserEvent, CliSideEffect>

            Given("Current State = Main Menu") {
                stateMachine = AppStateMachine().stateMachine.with { initialState(CliState.MainMenu) }
            }

            When("On UserEvent ViewOrgsMenu") {
                stateMachine.transition(UserEvent.OptionThree)
            }

            Then("Transitions to the OrgsMenu") {
                stateMachine.state shouldBeEqualTo CliState.TicketsMenu
            }
        }


        Scenario("StateMachine maintains same state on invalid transition") {

            lateinit var stateMachine: StateMachine<CliState, UserEvent, CliSideEffect>

            Given("Current State = Main Menu") {
                stateMachine = AppStateMachine().stateMachine.with { initialState(CliState.MainMenu) }
            }

            When("On UserEvent Proceed") {
                stateMachine.transition(UserEvent.Proceed)
            }

            Then("Does not transition") {
                stateMachine.state shouldBeEqualTo CliState.MainMenu
            }
        }

    }
})