package com.examples.abbasdgr8.model.domain

import java.util.*

data class Ticket(
        val _id: String,
        val url: String,
        val external_id: String,
        val created_at: Date,
        val type: String,
        val subject: String,
        val description: String,
        val priority: String,
        val status: String,
        val submitter_id: Int,
        val assignee_id: Int,
        val organization_id: Int,
        val tags: List<String>,
        val has_incidents: Boolean,
        val due_at: Date,
        val via: String
)