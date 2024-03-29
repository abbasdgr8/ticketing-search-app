package com.examples.abbasdgr8.model.service

import com.examples.abbasdgr8.model.data.InputJsonDeserializer
import com.examples.abbasdgr8.model.domain.Organization
import com.examples.abbasdgr8.model.domain.User
import com.examples.abbasdgr8.model.service.exceptions.UserSearchError
import org.amshove.kluent.*
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import java.io.File
import java.lang.Exception
import java.util.*

class UserSearchServiceTests: Spek({

    val service = UserSearchService()


    Feature("Searching on active flag") {

        Scenario("Searching with active flag true to obtain multiple matching records") {

            lateinit var results: List<User>

            When("Search by active is invoked") {
                results = service.findByField("active", true)
            }

            Then("39 out of 75 active records are returned") {
                results.shouldNotBeNull()
                results.size shouldBeEqualTo 39
            }
        }

        Scenario("Searching with active flag false to obtain multiple matching records") {

            lateinit var results: List<User>

            When("Search by active is invoked") {
                results = service.findByField("active", false)
            }

            Then("36 out of 75 inactive records are returned") {
                results.shouldNotBeNull()
                results.size shouldBeEqualTo 36
            }
        }
    }


    Feature("Partial search on locale field") {

        Scenario("Searching with locale containing en to obtain multiple matching records") {

            lateinit var results: List<User>

            When("Search by locale is invoked") {
                results = service.findByField("locale", "en")
            }

            Then("32 out of 75 en records are returned") {
                results.shouldNotBeNull()
                results.size shouldBeEqualTo 32
            }
        }

        Scenario("Searching with locale containing hh to obtain no matches") {

            lateinit var results: List<User>

            When("Search by locale is invoked") {
                results = service.findByField("locale", "hh")
            }

            Then("0 out of 75 inactive records are returned") {
                results.shouldNotBeNull()
                results.size shouldBeEqualTo 0
            }
        }
    }


    Feature("Searching on a field that does not exist") {

        Scenario("Seaching on field with name abc") {

            lateinit var exception: Exception

            When("findByField() using abc gets invoked") {
                try{
                    service.findByField("abc", "")
                } catch (e: Exception) {
                    exception = e
                }
            }

            Then("A UserSearchError gets thrown") {
                exception.shouldNotBeNull()
                exception shouldBeInstanceOf UserSearchError::class
                exception.cause shouldBeInstanceOf NoSuchElementException::class
            }
        }
    }


    Feature("List all searchable fields") {

        val expectedFields = listOf(
                                "_id",
                                "active",
                                "alias",
                                "email",
                                "external_id",
                                "locale",
                                "name",
                                "organization_id",
                                "phone",
                                "role",
                                "shared",
                                "signature",
                                "suspended",
                                "timezone",
                                "url",
                                "verified"
                            )

        Scenario("On User object") {

            lateinit var searchableFields: List<String>

            When("listAllSearchableFields is called") {
                searchableFields = service.getAllSearchableFieldNames()
            }

            Then("all correct searchable fields are returned") {
                searchableFields shouldBeEqualTo expectedFields
            }
        }
    }


    Feature("Get associated Org") {

        val userId = InputJsonDeserializer().readUsers(File("src/test/resources/json/valid/users.json"))[0]._id

        Scenario("For a valid, existing, and associated User object") {

            lateinit var associatedOrg: Organization

            When("Associations are queried") {
                associatedOrg = service.getAssociatedOrg(userId.toString())
            }

            Then("the correct associated org is returned") {
                associatedOrg.shouldNotBeNull()
                associatedOrg._id shouldBeEqualTo 119
            }
        }
    }


    Feature("findById") {

        Scenario("Searching using an id that is valid and belongs to a User record") {

            val userId = 1
            lateinit var user: User

            When("findById() using an valid and existing id gets invoked") {
                user = service.findById(userId.toString())!!
            }

            Then("A single user record is returned") {
                user.shouldNotBeNull()
                user._id shouldBeEqualTo userId
            }
        }

        Scenario("Searching using an id that is valid but does not belong to any User record") {

            val userId = -1
            var user: User? = null

            When("findById() using an valid but non-existent id gets invoked") {
                user = service.findById(userId.toString())
            }

            Then("A UserSearchError gets thrown") {
                user.shouldBeNull()
            }
        }

        Scenario("Searching using an id that is invalid") {

            lateinit var exception: Exception

            When("findById() using an invalid id gets invoked") {
                try{
                    service.findById("not an integer")
                } catch (e: Exception) {
                    exception = e
                }
            }

            Then("A UserSearchError gets thrown") {
                exception.shouldNotBeNull()
                exception shouldBeInstanceOf UserSearchError::class
                exception.cause shouldBeInstanceOf NumberFormatException::class
            }
        }
    }

})