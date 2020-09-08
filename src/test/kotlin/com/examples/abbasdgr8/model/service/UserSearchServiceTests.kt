package com.examples.abbasdgr8.model.service

import com.examples.abbasdgr8.model.domain.User
import com.examples.abbasdgr8.model.service.exceptions.UserSearchError
import org.amshove.kluent.*
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import java.lang.Exception
import java.text.SimpleDateFormat
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


    Feature("Searching by created_at date") {

        Scenario("Searching on created_at for an existing record") {

            val createdAt = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss X").parse("2016-04-15T05:19:46 -10:00")
            lateinit var results: List<User>

            When("Search by created_at is invoked") {
                results = service.findByField("created_at", createdAt)
            }

            Then("Matching record gets returned") {
                results.shouldNotBeNull()
                results.size shouldBeEqualTo 1
            }
        }

        Scenario("Searching on created_at for non existent record") {

            val createdAt = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss X").parse("2019-04-15T05:19:46 -10:00")
            lateinit var results: List<User>

            When("Search by created_at is invoked") {
                results = service.findByField("created_at", createdAt)
            }

            Then("No record gets returned") {
                results.shouldBeEmpty()
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
                                "created_at",
                                "email",
                                "external_id",
                                "last_login_at",
                                "locale",
                                "name",
                                "organization_id",
                                "phone",
                                "role",
                                "shared",
                                "signature",
                                "suspended",
                                "tags",
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

})