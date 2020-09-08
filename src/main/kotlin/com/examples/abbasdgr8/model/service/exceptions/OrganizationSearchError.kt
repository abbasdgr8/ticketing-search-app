package com.examples.abbasdgr8.model.service.exceptions

import java.lang.Exception

/**
 *  Error to denote issues while searching Organizations
 */
class OrganizationSearchError(cause: Throwable?): Exception(cause) { }
