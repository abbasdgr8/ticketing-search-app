package com.examples.abbasdgr8.model.service

import com.examples.abbasdgr8.model.data.InputDataDeserializer
import com.examples.abbasdgr8.model.domain.Ticket
import org.amshove.kluent.*
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import java.io.File

class TicketSearchServiceTests: Spek({

    val service = TicketSearchService()

    Feature("Searching on _id") {

        val expectedTicket = InputDataDeserializer()
                            .readTickets(File("src/test/resources/json/valid/tickets.json"))[0]

        Scenario("Searching on _id for a record that exists") {

            val id = "436bf9b0-1147-4c0a-8439-6f79833bff5b"
            lateinit var results: List<Ticket>

            When("Search by _id is invoked") {
                results = service.findByField("_id", id)
            }

            Then("Valid record gets returned") {
                results.shouldNotBeNull()
                results[0] shouldBeEqualTo expectedTicket
            }
        }

        Scenario("Searching on _id for a record that does not exist") {

            val id = "non existent id"
            lateinit var results: List<Ticket>

            When("Search by _id is invoked") {
                results = service.findByField("_id", id)
            }

            Then("No record gets returned") {
                results.shouldBeEmpty()
            }
        }
    }


    Feature("Searching on assignee_id") {

        Scenario("Searching on assignee_id for existing records") {

            val assigneeId = 24
            lateinit var results: List<Ticket>

            When("Search by assignee_id is invoked") {
                results = service.findByField("assignee_id", assigneeId)
            }

            Then("Multiple records get returned") {
                results.shouldNotBeNull()
                results.size shouldBeEqualTo 4
            }
        }

        Scenario("Searching on assignee_id for non existent records") {

            val assigneeId = -1
            lateinit var results: List<Ticket>

            When("Search by assignee_id is invoked") {
                results = service.findByField("assignee_id", assigneeId)
            }

            Then("No record gets returned") {
                results.shouldBeEmpty()
            }
        }

    }

})