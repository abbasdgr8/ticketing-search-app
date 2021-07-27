package com.examples.abbasdgr8.view

import com.examples.abbasdgr8.model.domain.Organization
import com.examples.abbasdgr8.view.ViewCommons.Companion.getFieldsWithBanner
import com.examples.abbasdgr8.view.ViewCommons.Companion.getRecordsWithBanner
import com.examples.abbasdgr8.view.ViewCommons.Companion.getScreen

class OrganizationsView {

    companion object {

        fun getOrganizationsMenu(): String {
            return orgMenu
        }

        fun getOrganizationsFields(fields: List<String>): String {
            return getFieldsWithBanner(fields, orgFieldsBanner)
        }

        fun getOrganizationRecord(organizations: List<Organization>): String {
            return getRecordsWithBanner(organizations)
        }

        private val orgMenu = getScreen("org-menu")
        private val orgFieldsBanner = getScreen("org-fields")
    }

}