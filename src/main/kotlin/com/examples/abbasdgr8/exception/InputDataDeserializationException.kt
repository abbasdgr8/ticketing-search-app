package com.examples.abbasdgr8.exception

import java.lang.Exception


class InputDataDeserializationException(message: String?, cause: Throwable?) : Exception(message, cause) {
    /**
     * Marker Exception with empty body
     */
}
