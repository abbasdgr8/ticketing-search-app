package com.examples.abbasdgr8.model.service

import java.lang.Exception

/**
 *  Exception to denote issues occuring when search is issued on a non-existent field on the target object
 */
class TicketSearchException(cause: Throwable?): Exception(cause) { }
