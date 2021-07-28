package com.examples.abbasdgr8.view

import com.examples.abbasdgr8.model.domain.Organization
import com.examples.abbasdgr8.view.ViewCommons.Companion.getFieldsWithBanner
import com.examples.abbasdgr8.view.ViewCommons.Companion.getScreen
import com.examples.abbasdgr8.view.ViewCommons.Companion.getSearchResults

class OrganizationsView {

    companion object {

        fun getOrganizationsMenu(): String {
            return orgMenu
        }

        fun getOrganizationsFields(fields: List<String>): String {
            return getFieldsWithBanner(fields, orgFieldsBanner)
        }

        fun getSearchResults(orgs: List<Organization>): String {
            return getSearchResults(orgs, "Organizations")
        }

        private val orgMenu = getScreen("org-menu")
        private val orgFieldsBanner = getScreen("org-fields")
    }

}