package com.examples.abbasdgr8.view

import com.examples.abbasdgr8.model.domain.Organization
import com.examples.abbasdgr8.model.domain.User
import com.examples.abbasdgr8.view.ViewCommons.Companion.getFieldsWithBanner
import com.examples.abbasdgr8.view.ViewCommons.Companion.getScreen
import com.examples.abbasdgr8.view.ViewCommons.Companion.getSearchResults

class UsersView {

    companion object {

        fun getUsersMenu(): String {
            return usersMenu
        }

        fun getUsersFields(fields: List<String>): String {
            return getFieldsWithBanner(fields, usersFieldsBanner)
        }

        fun getSearchResults(users: List<User>): String {
            return getSearchResults(users, "User")
        }

        fun getAssociationResults(user: User?, org: Organization): String {
            return getSearchResults(listOf(user), "User") +
                    getSearchResults(listOf(org), "Organization", true)
        }

        private val usersMenu = getScreen("users-menu")
        private val usersFieldsBanner = getScreen("users-fields")
    }

}