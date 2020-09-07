package com.examples.abbasdgr8.model.domain

import java.util.*

data class Organization(
        val _id: Int,
        val url: String,
        val external_id: String,
        val name: String,
        val alias: String,
        val domain_names: List<String>,
        val created_at: Date,
        val details: String,
        val shared_tickets: Boolean,
        val tags: List<String>
)