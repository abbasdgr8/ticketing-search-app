package com.examples.abbasdgr8

import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldNotBe
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

class InputDataDeserializerTests: Spek({

    Feature("Must be able to read JSON and deserialize tickets.json") {

        Scenario("Read tickets.json input file") {

            val ticketsFilePath = "src/test/resources/json/tickets.json"
            lateinit var tickets: String

            When("deserializer invoked") {
                val d10r = InputDataDeserializer()
                tickets = d10r.readTickets(ticketsFilePath)
            }

            Then("JSON objects get unmarshalled successfully") {
                tickets shouldNotBe null
            }
        }
    }
})