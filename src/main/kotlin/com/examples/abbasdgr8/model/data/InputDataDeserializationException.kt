package com.examples.abbasdgr8.model.data

import java.lang.Exception

/**
 * Exception to denote issues occuring during deserialization of the input JSON data
 */
class InputDataDeserializationException(cause: Throwable?) : Exception(cause) {
    /**
     * Marker Exception with empty body
     */
}
