package com.examples.abbasdgr8.controller

import com.examples.abbasdgr8.model.domain.Ticket
import com.examples.abbasdgr8.model.service.OrganizationSearchService
import com.examples.abbasdgr8.model.service.TicketSearchService
import com.examples.abbasdgr8.model.service.UserSearchService
import com.examples.abbasdgr8.view.IndexView
import com.examples.abbasdgr8.view.TicketsView
import com.examples.abbasdgr8.view.ViewCommons
import com.examples.abbasdgr8.view.constants.Prompts
import io.mockk.every
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldContain
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

class ViewControllerTests: Spek ({


    Feature("Search for a ticket based on _id and gracefully exit app") {

        val ticketSearchService = mockk<TicketSearchService>()
        val userSearchService = mockk<UserSearchService>()
        val orgSearchService = mockk<OrganizationSearchService>()

        val ticket = mockk<Ticket>()
        val tickets = listOf(ticket)

        every { ticketSearchService.getAllSearchableFieldNames() } returns TicketSearchService().getAllSearchableFieldNames()
        every { ticketSearchService.findByField(any(), any<String>()) } returns tickets

        val controller = ViewController(ticketSearchService, userSearchService, orgSearchService)

        Scenario("ViewController returns splash screen") {

            lateinit var userInput: String
            lateinit var view: String

            Given("No user input") {
                userInput = ""
            }

            When("processAction() is invoked") {
                view = controller.getInteraction(userInput).screen
            }

            Then("view matches Splash Screen") {
                view shouldBeEqualTo IndexView.getSplashScreen()
            }
        }


        Scenario("ViewController returns Main Menu on Proceed") {

            lateinit var userInput: String
            lateinit var view: String

            Given("User Input => Y") {
                userInput = "Y"
            }

            When("processAction() is invoked") {
                view = controller.getInteraction(userInput).screen
            }

            Then("view matches Main Menu") {
                view shouldBeEqualTo IndexView.getMainMenu()
            }
        }

        Scenario("ViewController returns Tickets Menu on Option 1") {

            lateinit var userInput: String
            lateinit var view: String

            Given("User Input => 1") {
                userInput = "1"
            }

            When("processAction() is invoked") {
                view = controller.getInteraction(userInput).screen
            }

            Then("view matches Tickets Menu") {
                view shouldBeEqualTo TicketsView.getTicketsMenu()
            }
        }

        Scenario("ViewController enters Search on Option 2") {

            lateinit var userInput: String
            lateinit var prompt: String

            Given("User Input => 2") {
                userInput = "2"
            }

            When("processAction() is invoked") {
                prompt = controller.getInteraction(userInput).prompt
            }

            Then("prompt matches search fields") {
                prompt shouldBeEqualTo Prompts.FIELD_NAME.text
            }
        }

        Scenario("ViewController enters Search Value on field name") {

            lateinit var userInput: String
            lateinit var prompt: String

            Given("User Input => assignee_id") {
                userInput = "assignee_id"
            }

            When("processAction() is invoked") {
                prompt = controller.getInteraction(userInput).prompt
            }

            Then("view matches search fields") {
                prompt shouldBeEqualTo Prompts.FIELD_VALUE.text
            }
        }

        Scenario("ViewController returns Ticket Record on field value") {

            lateinit var userInput: String
            lateinit var view: String

            Given("User Input => 24") {
                userInput = "24"
            }

            When("processAction() is invoked") {
                view = controller.getInteraction(userInput).screen
            }

            Then("view returns ticket record") {
                view shouldContain "Ticket"
            }
        }

        Scenario("ViewController returns Tickets Menu on Proceed") {

            lateinit var userInput: String
            lateinit var view: String

            Given("User Input => .") {
                userInput = "."
            }

            When("processAction() is invoked") {
                view = controller.getInteraction(userInput).screen
            }

            Then("view returns tickets menu") {
                view shouldBeEqualTo TicketsView.getTicketsMenu()
            }
        }

        Scenario("ViewController returns Main Menu on Option 3") {

            lateinit var userInput: String
            lateinit var view: String

            Given("User Input => 3") {
                userInput = "3"
            }

            When("processAction() is invoked") {
                view = controller.getInteraction(userInput).screen
            }

            Then("view returns main menu") {
                view shouldBeEqualTo IndexView.getMainMenu()
            }
        }

        Scenario("ViewController Exits on Option 3") {

            lateinit var userInput: String
            lateinit var view: String

            Given("User Input => 4") {
                userInput = "4"
            }

            When("processAction() is invoked") {
                view = controller.getInteraction(userInput).screen
            }

            Then("view returns main menu") {
                view shouldBeEqualTo IndexView.getEnd()
            }
        }

    }

})