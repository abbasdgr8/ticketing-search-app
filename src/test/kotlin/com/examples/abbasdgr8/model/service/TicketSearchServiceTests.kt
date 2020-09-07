package com.examples.abbasdgr8.model.service

import com.examples.abbasdgr8.model.data.InputDataDeserializer
import com.examples.abbasdgr8.model.domain.Ticket
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeNull
import org.amshove.kluent.shouldNotBeNull
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import java.io.File

class TicketSearchServiceTests: Spek({

    val service = TicketSearchService()

    Feature("Searching on ID") {

        Scenario("Searching on ID for a record that exists") {

            val id = "436bf9b0-1147-4c0a-8439-6f79833bff5b"
            val ticket = InputDataDeserializer().readTickets(File("src/test/resources/json/valid/tickets.json"))[0]
            var result: Ticket? = null

            When("Search by id is invoked") {
                result = service.findById(id)!!
            }

            Then("Valid record gets returned") {
                result.shouldNotBeNull()
                result shouldBeEqualTo ticket
            }

        }

        Scenario("Searching on ID for a record that does not exist") {

            val id = "non existent id"
            var result: Ticket? = null

            When("Search by id is invoked") {
                result = service.findById(id)
            }

            Then("Valid record gets returned") {
                result.shouldBeNull()
            }

        }

    }

})