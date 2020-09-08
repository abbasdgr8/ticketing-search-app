package com.examples.abbasdgr8.model.service

import java.lang.Exception

/**
 *  Error to denote issues while searching Tickets
 */
class TicketSearchError(cause: Throwable?): Exception(cause) { }
