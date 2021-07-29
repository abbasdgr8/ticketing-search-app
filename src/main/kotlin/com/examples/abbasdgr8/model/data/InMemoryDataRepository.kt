package com.examples.abbasdgr8.model.data

import com.examples.abbasdgr8.model.domain.Organization
import com.examples.abbasdgr8.model.domain.Ticket
import com.examples.abbasdgr8.model.domain.User
import java.io.File

/**
 * This class loads and stores all the data relevant for this application in memory
 */
class InMemoryDataRepository {

    companion object {

        lateinit var tickets: List<Ticket>
        lateinit var users: List<User>
        lateinit var organizations: List<Organization>

        init {
            loadDataIntoMemory()
        }

        /**
         * Currently just loads data into memory without indexing.
         * Indexing to be considered as an enhancement later to improve search response times
         */
        private fun loadDataIntoMemory() {
            val deserializer = InputJsonDeserializer()

            tickets = deserializer.readTickets(File("src/main/resources/input-data/tickets.json"))
            users = deserializer.readUsers(File("src/main/resources/input-data/users.json"))
            organizations = deserializer.readOrganizations(File("src/main/resources/input-data/organizations.json"))
        }
    }
}