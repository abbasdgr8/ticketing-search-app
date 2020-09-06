package com.examples.abbasdgr8

import com.examples.abbasdgr8.model.Ticket
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldNotBeNull
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import java.io.File

class InputDataDeserializerTests: Spek({

    Feature("Must be able to read JSON and deserialize tickets.json") {

        val ticketsFile = File("src/test/resources/json/tickets.json")
        val d10r = InputDataDeserializer()

        Scenario("Unmarshall tickets.json input data") {

            lateinit var tickets: List<Ticket>

            When("deserializer invoked") {
                tickets = d10r.readTickets(ticketsFile)
            }

            Then("JSON objects get deserialized successfully") {
                tickets.shouldNotBeNull()
                tickets shouldBeInstanceOf List::class
            }
        }
    }
})