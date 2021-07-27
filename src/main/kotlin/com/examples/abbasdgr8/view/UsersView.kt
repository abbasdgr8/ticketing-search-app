package com.examples.abbasdgr8.view

import com.examples.abbasdgr8.model.domain.User
import com.examples.abbasdgr8.view.ViewCommons.Companion.getFieldsWithBanner
import com.examples.abbasdgr8.view.ViewCommons.Companion.getRecordsWithBanner
import com.examples.abbasdgr8.view.ViewCommons.Companion.getScreen

class UsersView {

    companion object {

        fun getUsersMenu(): String {
            return usersMenu
        }

        fun getUsersFields(fields: List<String>): String {
            return getFieldsWithBanner(fields, usersFieldsBanner)
        }

        fun getUserRecord(users: List<User>): String {
            return getRecordsWithBanner(users)
        }

        private val usersMenu = getScreen("users-menu")
        private val usersFieldsBanner = getScreen("users-fields")
    }

}