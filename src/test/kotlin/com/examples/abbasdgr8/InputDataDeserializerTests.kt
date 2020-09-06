package com.examples.abbasdgr8

import com.examples.abbasdgr8.model.Ticket
import org.amshove.kluent.shouldBeEqualTo
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

            Then("All properties of Ticket object should have expected values") {
                tickets[0]._id shouldBeEqualTo "436bf9b0-1147-4c0a-8439-6f79833bff5b"
                tickets[0].url shouldBeEqualTo "http://initech.zendesk.com/api/v2/tickets/436bf9b0-1147-4c0a-8439-6f79833bff5b.json"
                tickets[0].external_id shouldBeEqualTo "9210cdc9-4bee-485f-a078-35396cd74063"
                tickets[0].created_at.toString() shouldBeEqualTo "2016-04-28T11:19:34 -10:00"
                tickets[0].type shouldBeEqualTo "incident"
                tickets[0].subject shouldBeEqualTo "A Catastrophe in Korea (North)"
                tickets[0].description shouldBeEqualTo "Nostrud ad sit velit cupidatat laboris ipsum nisi amet laboris ex exercitation amet et proident. Ipsum fugiat aute dolore tempor nostrud velit ipsum."
                tickets[0].priority shouldBeEqualTo "high"
                tickets[0].status shouldBeEqualTo "pending"
                tickets[0].submitter_id shouldBeEqualTo 38
                tickets[0].assignee_id shouldBeEqualTo 24
                tickets[0].organization_id shouldBeEqualTo 116
                tickets[0].tags shouldBeEqualTo listOf("Ohio", "Pennsylvania", "American Samoa", "Northern Mariana Islands")
                tickets[0].has_incidents shouldBeEqualTo false
                tickets[0].due_at shouldBeEqualTo "2016-07-31T02:37:50 -10:00"
                tickets[0].via shouldBeEqualTo "web"
            }
        }
    }
})