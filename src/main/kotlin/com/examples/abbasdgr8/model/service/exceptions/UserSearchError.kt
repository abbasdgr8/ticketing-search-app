package com.examples.abbasdgr8.model.service.exceptions

import java.lang.Exception

/**
 *  Error to denote issues while searching users
 */
class UserSearchError(cause: Throwable?): Exception(cause) { }
