package com.examples.abbasdgr8.view

import com.examples.abbasdgr8.model.domain.User
import java.lang.StringBuilder

class UsersView {

    companion object {

        fun getUsersMenu(): String {
            return usersMenu
        }

        fun getUsersFields(fields: List<String>): String {
            val sb = StringBuilder()
            fields.forEach {field ->
                sb.append("\n")
                sb.append(field)
            }
            return usersFields + sb.toString()
        }

        fun getUsersFieldName(): String {
            return usersFieldName
        }

        fun getUsersFieldValue(): String {
            return usersFieldValue
        }

        fun getUserRecord(users: List<User>): String {
            val sb = StringBuilder()
            users.forEach { user ->
                sb.append("\n")
                sb.append(user.toString())
                sb.append("\n")
            }
            sb.append("\n")
            sb.append("Found ${users.size} results")

            return sb.toString()
        }

        private const val usersMenu = """                 
                                    |-------------------------------------------------------|
                                    |-------------------- USERS MENU ---------------------|
                                    |-------------------------------------------------------|
                                    | Select Search Options:                                |
                                    |                                                       |
                                    | Press 1 to view all Searchable fields on Users      |
                                    | Press 2 to search                                     |
                                    | Press 3 to go to back to the Main Menu                |
                                    | ------------------------------------------------------|
                                    """

        private const val usersFields = """                 
                                    |-------------------------------------------------------|
                                    |------------------- USERS FIELDS --------------------|
                                    |-------------------------------------------------------|
                                    | Enter '.' followed by 'Enter' to Proceed              |
                                    """

        private const val usersFieldName = """                 
                                    Please enter name of the field that you want to search by:
                                    """

        private const val usersFieldValue = """                 
                                    Please enter value of the field that you want to search by:
                                    """
    }

}