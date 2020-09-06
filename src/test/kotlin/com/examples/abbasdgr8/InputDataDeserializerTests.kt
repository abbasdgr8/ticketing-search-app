package com.examples.abbasdgr8

import com.examples.abbasdgr8.model.Ticket
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldNotBeEmpty
import org.amshove.kluent.shouldNotBeNull
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import java.io.File
import java.util.*

class InputDataDeserializerTests: Spek({

    Feature("Must be able to read and deserialize tickets.json") {

        val ticketsFile = File("src/test/resources/json/tickets.json")
        val deserializer = InputDataDeserializer()

        Scenario("Unmarshall tickets.json input data") {

            lateinit var tickets: List<Ticket>

            When("deserializer invoked") {
                tickets = deserializer.readTickets(ticketsFile)
            }

            Then("JSON objects get deserialized successfully") {
                tickets.shouldNotBeNull()
                tickets shouldBeInstanceOf List::class
            }

            Then("Returns List of Ticket model objects, and is not empty") {
                tickets.shouldNotBeEmpty()
                tickets[0] shouldBeInstanceOf Ticket::class
            }

            Then("All properties of Ticket object should have expected types") {
                tickets[0]._id shouldBeInstanceOf String::class
                tickets[0].url shouldBeInstanceOf String::class
                tickets[0].external_id shouldBeInstanceOf String::class
                tickets[0].created_at shouldBeInstanceOf Date::class
                tickets[0].type shouldBeInstanceOf String::class
                tickets[0].subject shouldBeInstanceOf String::class
                tickets[0].description shouldBeInstanceOf String::class
                tickets[0].priority shouldBeInstanceOf String::class
                tickets[0].status shouldBeInstanceOf String::class
                tickets[0].submitter_id shouldBeInstanceOf Int::class
                tickets[0].assignee_id shouldBeInstanceOf Int::class
                tickets[0].organization_id shouldBeInstanceOf Int::class
                tickets[0].tags shouldBeInstanceOf List::class
                tickets[0].has_incidents shouldBeInstanceOf Boolean::class
                tickets[0].due_at shouldBeInstanceOf Date::class
                tickets[0].via shouldBeInstanceOf String::class
            }
        }
    }
})