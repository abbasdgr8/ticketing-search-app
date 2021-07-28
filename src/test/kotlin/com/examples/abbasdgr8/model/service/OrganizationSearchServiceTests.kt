package com.examples.abbasdgr8.model.service

import com.examples.abbasdgr8.model.data.InputDataDeserializer
import com.examples.abbasdgr8.model.domain.Organization
import com.examples.abbasdgr8.model.domain.Ticket
import com.examples.abbasdgr8.model.domain.User
import com.examples.abbasdgr8.model.service.exceptions.OrganizationSearchError
import org.amshove.kluent.*
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import java.io.File
import java.lang.Exception
import java.util.NoSuchElementException

class OrganizationSearchServiceTests: Spek({

    val service = OrganizationSearchService()


    Feature("Searching on _id") {

        val expectedOrganization = InputDataDeserializer()
                            .readOrganizations(File("src/test/resources/json/valid/organizations.json"))[0]

        Scenario("Searching on _id for a record that exists") {

            val id = 101
            lateinit var results: List<Organization>

            When("Search by _id is invoked") {
                results = service.findByField("_id", id)
            }

            Then("Valid record gets returned") {
                results.shouldNotBeNull()
                results[0] shouldBeEqualTo expectedOrganization
            }
        }

        Scenario("Searching on _id for a record that does not exist") {

            val id = -1
            lateinit var results: List<Organization>

            When("Search by _id is invoked") {
                results = service.findByField("_id", id)
            }

            Then("No record gets returned") {
                results.shouldBeEmpty()
            }
        }
    }


    Feature("Searching on details") {

        Scenario("Searching on details for existing records") {

            val details = "MegaCorp"
            lateinit var results: List<Organization>

            When("Search by details is invoked") {
                results = service.findByField("details", details)
            }

            Then("Multiple records get returned") {
                results.shouldNotBeNull()
                results.size shouldBeEqualTo 9
            }
        }

        Scenario("Searching on details for non existent records") {

            val details = "Random string"
            lateinit var results: List<Organization>

            When("Search by details is invoked") {
                results = service.findByField("details", details)
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
                    service.findByField("xyz", "")
                } catch (e: Exception) {
                    exception = e
                }
            }

            Then("An OrganizationSearchError gets thrown") {
                exception.shouldNotBeNull()
                exception shouldBeInstanceOf OrganizationSearchError::class
                exception.cause shouldBeInstanceOf NoSuchElementException::class
            }
        }
    }


    Feature("List all searchable fields") {

        val expectedFields = listOf(
                                "_id",
                                "alias",
                                "details",
                                "external_id",
                                "name",
                                "shared_tickets",
                                "url"
                            )

        Scenario("On Organization object") {

            lateinit var searchableFields: List<String>

            When("listAllSearchableFields is called") {
                searchableFields = service.getAllSearchableFieldNames()
            }

            Then("all correct searchable fields are returned") {
                searchableFields shouldBeEqualTo expectedFields
            }

        }

    }


    Feature("Get associated Tickets & Users") {

        val org = InputDataDeserializer()
            .readOrganizations(File("src/test/resources/json/valid/organizations.json"))[0]

        Scenario("For a valid, existing, and associated Organization object") {

            lateinit var associatedTicketsAndUsers: Pair<Set<Ticket>, Set<User>>

            When("Associations are queried") {
                associatedTicketsAndUsers = service.getAssociatedTicketsAndUsers(org)
            }

            Then("all the correct associated tickets and users are returned") {
                associatedTicketsAndUsers.shouldNotBeNull()

                val associatedTickets = associatedTicketsAndUsers.first
                associatedTickets.shouldNotBeNull()
                associatedTickets.shouldNotBeEmpty()
                associatedTickets.size shouldBeEqualTo 4

                val associatedUsers = associatedTicketsAndUsers.second
                associatedUsers.shouldNotBeNull()
                associatedUsers.shouldNotBeEmpty()
                associatedUsers.size shouldBeEqualTo 4
            }
        }
    }
})