package com.examples.abbasdgr8.model.service

import com.examples.abbasdgr8.model.data.InputDataDeserializer
import com.examples.abbasdgr8.model.domain.Organization
import com.examples.abbasdgr8.model.domain.Ticket
import com.examples.abbasdgr8.model.domain.User
import com.examples.abbasdgr8.model.service.exceptions.TicketSearchError
import org.amshove.kluent.*
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import java.io.File
import java.lang.Exception
import java.util.NoSuchElementException

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


    Feature("Searching on subject") {

        Scenario("Searching on subject for existing records") {

            val subject = "A Catastrophe in Korea (North)"
            lateinit var results: List<Ticket>

            When("Search by subject is invoked") {
                results = service.findByField("subject", subject)
            }

            Then("Multiple records get returned") {
                results.shouldNotBeNull()
                results.size shouldBeEqualTo 1
            }
        }

        Scenario("Searching on subject for non existent records") {

            val subject = "Random string"
            lateinit var results: List<Ticket>

            When("Search by subject is invoked") {
                results = service.findByField("subject", subject)
            }

            Then("No record gets returned") {
                results.shouldBeEmpty()
            }
        }
    }


    Feature("Searching on a field that does not exist") {

        Scenario("Seaching on field with name xyz") {

            lateinit var exception: Exception

            When("findByField() using xyz gets invoked") {
                try{
                    service.findByField("xyz", "random value")
                } catch (e: Exception) {
                    exception = e
                }
            }

            Then("A TicketSearchError gets thrown") {
                exception.shouldNotBeNull()
                exception shouldBeInstanceOf TicketSearchError::class
                exception.cause shouldBeInstanceOf NoSuchElementException::class
            }
        }
    }


    Feature("List all searchable fields") {

        val expectedFields = listOf(
                                "_id",
                                "assignee_id",
                                "description",
                                "external_id",
                                "has_incidents",
                                "organization_id",
                                "priority",
                                "status",
                                "subject",
                                "submitter_id",
                                "type",
                                "url",
                                "via"
                            )

        Scenario("On Ticket object") {

            lateinit var searchableFields: List<String>

            When("listAllSearchableFields is called") {
                searchableFields = service.getAllSearchableFieldNames()
            }

            Then("all correct searchable fields are returned") {
                searchableFields shouldBeEqualTo expectedFields
            }
        }
    }


    Feature("Get associated Org & Users") {

        val ticketId = InputDataDeserializer().readTickets(File("src/test/resources/json/valid/tickets.json"))[0]._id

        Scenario("For a valid, existing, and associated Ticket object") {

            lateinit var associatedOrgAndUsers: Pair<Organization?, Set<User>>

            When("Associations are queried") {
                associatedOrgAndUsers = service.getAssociatedOrgAndUsers(ticketId)
            }

            Then("the correct associated org and users are returned") {
                associatedOrgAndUsers.shouldNotBeNull()

                val associatedOrg = associatedOrgAndUsers.first
                associatedOrg.shouldNotBeNull()
                associatedOrg._id shouldBeEqualTo 116

                val associatedUsers = associatedOrgAndUsers.second
                associatedUsers.shouldNotBeNull()
                associatedUsers.shouldNotBeEmpty()
                associatedUsers.size shouldBeEqualTo 2
            }
        }
    }

})