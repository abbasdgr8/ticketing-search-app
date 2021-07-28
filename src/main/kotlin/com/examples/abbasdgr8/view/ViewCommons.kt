package com.examples.abbasdgr8.view

import java.io.File
import kotlin.text.StringBuilder

class ViewCommons {

    companion object {

        fun getScreen(fileName: String): String {
            return File("$SCREENS_DIR_PATH/$fileName.txt").readText()
        }

        fun getFieldsWithBanner(fields: List<String>, banner: String): String {
            val sb = StringBuilder()
            fields.forEach {field ->
                sb.append(System.lineSeparator())
                sb.append(field)
            }
            sb.append(System.lineSeparator())
            sb.append(System.lineSeparator())
            return banner + sb.toString()
        }

        fun <T> getSearchResults(records: List<T>, recordType: String, associationSearch: Boolean = false): String {
            val sb = StringBuilder()
            sb.append(getSearchedRecords(records))
            sb.append(getSearchResultSummary(records.size, recordType, associationSearch))
            return sb.toString()
        }

        fun getFieldNameErrorMsg(): String {
            return fieldNameErrorMsg
        }

        fun getFieldValueErrorMsg(): String {
            return fieldValueErrorMsg
        }

        fun getAssocationsErrorMsg(): String {
            return assocationsErrorMsg
        }

        private fun <T> getSearchedRecords(records: List<T>): String {
            val sb = StringBuilder()
            records.forEach { record ->
                sb.append(System.lineSeparator())
                sb.append(record.toString())
                sb.append(System.lineSeparator())
            }
            return sb.toString()
        }

        private fun getSearchResultSummary(count: Int, recordType: String, associationSearch: Boolean = false): String {
            val sb = StringBuilder()
            sb.append(System.lineSeparator())
            if (associationSearch) {
                sb.append("Found $count associated $recordType(s)")
            } else {
                sb.append("Found $count matching $recordType(s)")
            }
            sb.append(System.lineSeparator())
            return sb.toString()
        }

        private const val SCREENS_DIR_PATH = "src/main/resources/screens"

        private val fieldNameErrorMsg = getScreen("field-name-error")
        private val fieldValueErrorMsg = getScreen("field-value-error")
        private val assocationsErrorMsg = getScreen("assocations-error")
    }
}