package com.examples.abbasdgr8.model.domain

import java.util.*

data class User(
        val _id: Int,
        val url: String,
        val external_id: String,
        val name: String,
        val alias: String,
        val created_at: Date,
        val active: Boolean,
        val verified: Boolean,
        val shared: Boolean,
        val locale: String,
        val timezone: String,
        val last_login_at: Date,
        val email: String,
        val phone: String,
        val signature: String,
        val organization_id: Int,
        val tags: List<String>,
        val suspended: Boolean,
        val role: String
)