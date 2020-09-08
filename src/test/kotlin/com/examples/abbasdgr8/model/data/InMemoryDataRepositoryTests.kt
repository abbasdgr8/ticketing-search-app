package com.examples.abbasdgr8.model.data

import com.examples.abbasdgr8.model.domain.Organization
import com.examples.abbasdgr8.model.domain.Ticket
import com.examples.abbasdgr8.model.domain.User
import org.amshove.kluent.shouldBeEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

class InMemoryDataRepositoryTests: Spek ({

    Feature("All given data loaded into memory successfully") {

        Scenario("InMemoryDataRepository class") {

            lateinit var tickets: List<Ticket>
            lateinit var users: List<User>
            lateinit var organizations: List<Organization>

            When("Properties of repository accessed") {
                tickets = InMemoryDataRepository.tickets
                users = InMemoryDataRepository.users
                organizations = InMemoryDataRepository.organizations
            }

            Then("All properties populated with relevant record sets") {
                tickets.size shouldBeEqualTo 200
                users.size shouldBeEqualTo 75
                organizations.size shouldBeEqualTo 25
            }

        }

    }

})