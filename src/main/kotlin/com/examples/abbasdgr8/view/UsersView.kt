package com.examples.abbasdgr8.view

import com.examples.abbasdgr8.model.domain.User
import com.examples.abbasdgr8.view.ViewCommons.Companion.getScreen

class UsersView {

    companion object {

        fun getUsersMenu(): String {
            return usersMenu
        }

        fun getUsersFields(fields: List<String>): String {
            return ViewCommons.getFieldsWithBanner(fields, usersFieldsBanner)
        }

        fun getUserRecord(users: List<User>): String {
            return ViewCommons.getRecordsWithBanner(users)
        }

        private val usersMenu = getScreen("users-menu.txt")
        private val usersFieldsBanner = getScreen("users-fields.txt")
    }

}