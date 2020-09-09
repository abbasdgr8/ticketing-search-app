package com.examples.abbasdgr8.view

import com.examples.abbasdgr8.model.domain.Organization
import java.lang.StringBuilder

class OrganizationsView {

    companion object {

        fun getOrganizationsMenu(): String {
            return organizationsMenu
        }

        fun getOrganizationsFields(fields: List<String>): String {
            val sb = StringBuilder()
            fields.forEach {field ->
                sb.append("\n")
                sb.append(field)
            }
            return organizationsFields + sb.toString()
        }

        fun getOrganizationsFieldName(): String {
            return organizationsFieldName
        }

        fun getOrganizationsFieldValue(): String {
            return organizationsFieldValue
        }

        fun getOrganizationRecord(organizations: List<Organization>): String {
            val sb = StringBuilder()
            organizations.forEach { organization ->
                sb.append("\n")
                sb.append(organization.toString())
                sb.append("\n")
            }
            sb.append("\n")
            sb.append("Found ${organizations.size} results")

            return sb.toString()
        }

        private const val organizationsMenu = """                 
                                    |-------------------------------------------------------|
                                    |------------------ ORGANIZATIONS MENU -----------------|
                                    |-------------------------------------------------------|
                                    | Select Search Options:                                |
                                    |                                                       |
                                    | Press 1 to view all Searchable fields on Organization |
                                    | Press 2 to search                                     |
                                    | Press 3 to go to back to the Main Menu                |
                                    | ------------------------------------------------------|
                                    """

        private const val organizationsFields = """                 
                                    |-------------------------------------------------------|
                                    |---------------- ORGANIZATIONS FIELDS -----------------|
                                    |-------------------------------------------------------|
                                    | Enter '.' followed by 'Enter' to Proceed              |
                                    """

        private const val organizationsFieldName = """                 
                                    Please enter name of the field that you want to search by:
                                    """

        private const val organizationsFieldValue = """                 
                                    Please enter value of the field that you want to search by:
                                    """
    }

}