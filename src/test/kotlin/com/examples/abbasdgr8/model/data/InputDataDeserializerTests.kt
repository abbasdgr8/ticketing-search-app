package com.examples.abbasdgr8.model.data

import com.examples.abbasdgr8.model.domain.Organization
import com.examples.abbasdgr8.model.domain.Ticket
import com.examples.abbasdgr8.model.domain.User
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

    val deserializer = InputDataDeserializer()

    Feature("Read and deserialize tickets.json") {

        Scenario("Unmarshal valid tickets.json input data") {

            val validTicketsFile = File("src/test/resources/json/valid/tickets.json")
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
                tickets.size shouldBeEqualTo 1

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

        Scenario("Unmarshal invalid tickets JSON input data") {

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



    Feature("Read and deserialize users.json") {

        Scenario("Unmarshal valid users.json input data") {

            val validUsersFile = File("src/test/resources/json/valid/users.json")
            lateinit var users: List<User>
            lateinit var user: User

            When("deserializer invoked") {
                users = deserializer.readUsers(validUsersFile)
            }

            Then("JSON objects get deserialized successfully") {
                users.shouldNotBeNull()
                users shouldBeInstanceOf List::class
            }

            Then("Returns List of User model objects, and is not empty") {
                users.shouldNotBeEmpty()
                users.size shouldBeEqualTo 1

                user = users[0]
                user shouldBeInstanceOf User::class
            }

            Then("All properties of User object should have expected types") {
                user._id shouldBeInstanceOf Int::class
                user.url shouldBeInstanceOf String::class
                user.external_id shouldBeInstanceOf String::class
                user.name shouldBeInstanceOf String::class
                user.alias shouldBeInstanceOf String::class
                user.created_at shouldBeInstanceOf Date::class
                user.active shouldBeInstanceOf Boolean::class
                user.verified shouldBeInstanceOf Boolean::class
                user.shared shouldBeInstanceOf Boolean::class
                user.locale shouldBeInstanceOf String::class
                user.timezone shouldBeInstanceOf String::class
                user.last_login_at shouldBeInstanceOf Date::class
                user.email shouldBeInstanceOf String::class
                user.phone shouldBeInstanceOf String::class
                user.signature shouldBeInstanceOf String::class
                user.organization_id shouldBeInstanceOf Int::class
                user.tags shouldBeInstanceOf List::class
                user.suspended shouldBeInstanceOf Boolean::class
                user.role shouldBeInstanceOf String::class
            }

            Then("All properties of User object should have expected values") {
                user._id shouldBeEqualTo 1
                user.url shouldBeEqualTo "http://initech.zendesk.com/api/v2/users/1.json"
                user.external_id shouldBeEqualTo "74341f74-9c79-49d5-9611-87ef9b6eb75f"
                user.name shouldBeEqualTo "Francisca Rasmussen"
                user.alias shouldBeEqualTo "Miss Coffey"
                user.created_at.toString() shouldBeEqualTo "Sat Apr 16 01:19:46 AEST 2016"
                user.active shouldBeEqualTo true
                user.verified shouldBeEqualTo true
                user.shared shouldBeEqualTo false
                user.locale shouldBeEqualTo "en-AU"
                user.timezone shouldBeEqualTo "Sri Lanka"
                user.last_login_at.toString() shouldBeEqualTo "Sun Aug 04 21:03:27 AEST 2013"
                user.email shouldBeEqualTo "coffeyrasmussen@flotonic.com"
                user.phone shouldBeEqualTo "8335-422-718"
                user.signature shouldBeEqualTo "Don't Worry Be Happy!"
                user.organization_id shouldBeEqualTo 119
                user.tags shouldBeEqualTo listOf("Springville", "Sutton", "Hartsville/Hartley", "Diaperville")
                user.suspended shouldBeEqualTo true
                user.role shouldBeEqualTo "admin"
            }
        }

        Scenario("Unmarshal invalid users JSON input data") {

            val invalidUsersFile = File("src/test/resources/json/invalid/users.json")
            lateinit var exception: Exception

            When("deserializer invoked") {
                try {
                    deserializer.readUsers(invalidUsersFile)
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



    Feature("Read and deserialize organizations JSON") {

        Scenario("Unmarshal valid organizations.json input data") {

            val validOrgFile = File("src/test/resources/json/valid/organizations.json")
            lateinit var organizations: List<Organization>
            lateinit var organization: Organization

            When("deserializer invoked") {
                organizations = deserializer.readOrganizations(validOrgFile)
            }

            Then("JSON objects get deserialized successfully") {
                organizations.shouldNotBeNull()
                organizations shouldBeInstanceOf List::class
            }

            Then("Returns List of Organization model objects, and is not empty") {
                organizations.shouldNotBeEmpty()
                organizations.size shouldBeEqualTo 1

                organization = organizations[0]
                organization shouldBeInstanceOf Organization::class
            }

            Then("All properties of Organization object should have expected types") {
                organization._id shouldBeInstanceOf Int::class
                organization.url shouldBeInstanceOf String::class
                organization.external_id shouldBeInstanceOf String::class
                organization.name shouldBeInstanceOf String::class
                organization.domain_names shouldBeInstanceOf List::class
                organization.created_at shouldBeInstanceOf Date::class
                organization.details shouldBeInstanceOf String::class
                organization.shared_tickets shouldBeInstanceOf Boolean::class
                organization.tags shouldBeInstanceOf List::class
            }

            Then("All properties of Organization object should have expected values") {
                organization._id shouldBeEqualTo 101
                organization.url shouldBeEqualTo "http://initech.zendesk.com/api/v2/organizations/101.json"
                organization.external_id shouldBeEqualTo "9270ed79-35eb-4a38-a46f-35725197ea8d"
                organization.name shouldBeEqualTo "Enthaze"
                organization.domain_names shouldBeEqualTo listOf("kage.com", "ecratic.com", "endipin.com", "zentix.com")
                organization.created_at.toString() shouldBeEqualTo "Sun May 22 07:10:28 AEST 2016"
                organization.details shouldBeEqualTo "MegaCorp"
                organization.shared_tickets shouldBeEqualTo false
                organization.tags shouldBeEqualTo listOf("Fulton", "West", "Rodriguez", "Farley")
            }
        }

        Scenario("Unmarshal invalid organizations JSON input data") {

            val invalidOrgFile = File("src/test/resources/json/invalid/organizations.json")
            lateinit var exception: Exception

            When("deserializer invoked") {
                try {
                    deserializer.readOrganizations(invalidOrgFile)
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