package com.examples.abbasdgr8

import com.examples.abbasdgr8.exception.InputDataDeserializationException
import com.examples.abbasdgr8.model.Ticket
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.amshove.kluent.shouldNotBeEmpty
import org.amshove.kluent.shouldNotBeNull
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import java.io.File
import java.lang.Exception
import java.util.*

class InputDataDeserializerTests: Spek({

    Feature("Must be able to read and deserialize tickets.json") {

        val deserializer = InputDataDeserializer()

        Scenario("Unmarshall valid tickets.json input data") {

            val validTicketsFile = File("src/test/resources/json/tickets.json")
            lateinit var tickets: List<Ticket>
            lateinit var ticket: Ticket

            When("deserializer invoked") {
                tickets = deserializer.readTickets(validTicketsFile)
            }

            Then("JSON objects get deserialized successfully") {
                tickets.shouldNotBeNull()
                tickets shouldBeInstanceOf List::class
            }

            Then("Returns List of Ticket model objects, and is not empty") {
                tickets.shouldNotBeEmpty()
                tickets.size shouldBeEqualTo 200

                ticket = tickets[0]
                ticket shouldBeInstanceOf Ticket::class
            }

            Then("All properties of Ticket object should have expected types") {
                ticket._id shouldBeInstanceOf String::class
                ticket.url shouldBeInstanceOf String::class
                ticket.external_id shouldBeInstanceOf String::class
                ticket.created_at shouldBeInstanceOf Date::class
                ticket.type shouldBeInstanceOf String::class
                ticket.subject shouldBeInstanceOf String::class
                ticket.description shouldBeInstanceOf String::class
                ticket.priority shouldBeInstanceOf String::class
                ticket.status shouldBeInstanceOf String::class
                ticket.submitter_id shouldBeInstanceOf Int::class
                ticket.assignee_id shouldBeInstanceOf Int::class
                ticket.organization_id shouldBeInstanceOf Int::class
                ticket.tags shouldBeInstanceOf List::class
                ticket.has_incidents shouldBeInstanceOf Boolean::class
                ticket.due_at shouldBeInstanceOf Date::class
                ticket.via shouldBeInstanceOf String::class
            }

            Then("All properties of Ticket object should have expected values") {
                ticket._id shouldBeEqualTo "436bf9b0-1147-4c0a-8439-6f79833bff5b"
                ticket.url shouldBeEqualTo "http://initech.zendesk.com/api/v2/tickets/436bf9b0-1147-4c0a-8439-6f79833bff5b.json"
                ticket.external_id shouldBeEqualTo "9210cdc9-4bee-485f-a078-35396cd74063"
                ticket.created_at.toString() shouldBeEqualTo "Fri Apr 29 07:19:34 AEST 2016"
                ticket.type shouldBeEqualTo "incident"
                ticket.subject shouldBeEqualTo "A Catastrophe in Korea (North)"
                ticket.description shouldBeEqualTo "Nostrud ad sit velit cupidatat laboris ipsum nisi amet laboris ex exercitation amet et proident. Ipsum fugiat aute dolore tempor nostrud velit ipsum."
                ticket.priority shouldBeEqualTo "high"
                ticket.status shouldBeEqualTo "pending"
                ticket.submitter_id shouldBeEqualTo 38
                ticket.assignee_id shouldBeEqualTo 24
                ticket.organization_id shouldBeEqualTo 116
                ticket.tags shouldBeEqualTo listOf("Ohio", "Pennsylvania", "American Samoa", "Northern Mariana Islands")
                ticket.has_incidents shouldBeEqualTo false
                ticket.due_at.toString() shouldBeEqualTo "Sun Jul 31 22:37:50 AEST 2016"
                ticket.via shouldBeEqualTo "web"
            }
        }

        Scenario("Unmarshall invalid tickets JSON input data") {

            val invalidTicketsFile = File("src/test/resources/json/invalid/tickets.json")
            lateinit var exception: Exception

            When("deserializer invoked") {
                try {
                    deserializer.readTickets(invalidTicketsFile)
                } catch (e: Exception) {
                    exception = e
                }
            }

            Then("JSON objects deserialization throws exception") {
                exception.shouldNotBeNull()
                exception shouldBeInstanceOf InputDataDeserializationException::class
            }
        }
    }
})