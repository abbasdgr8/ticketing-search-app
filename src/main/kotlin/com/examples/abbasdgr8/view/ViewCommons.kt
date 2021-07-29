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
                lineBreak(sb)
                sb.append(field)
            }
            lineBreak(sb)
            lineBreak(sb)
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

        fun lineBreak(sb: StringBuilder) {
            sb.append(System.lineSeparator())
        }

        fun horizontalRule(sb: StringBuilder) {
            sb.append(horizontalRule)
        }

        private fun <T> getSearchedRecords(records: List<T>): String {
            val sb = StringBuilder()
            records.forEach { record ->
                lineBreak(sb)
                sb.append(record.toString())
                lineBreak(sb)
            }
            return sb.toString()
        }

        private fun getSearchResultSummary(count: Int, recordType: String, isAssociationSearch: Boolean = false): String {
            val sb = StringBuilder()
            val searchType: String = if (isAssociationSearch) "associated" else "matching"
            lineBreak(sb)
            horizontalRule(sb)
            lineBreak(sb)
            sb.append("Found $count $searchType $recordType(s)")
            lineBreak(sb)
            horizontalRule(sb)
            lineBreak(sb)
            return sb.toString()
        }

        private const val SCREENS_DIR_PATH = "src/main/resources/screens"

        private val fieldNameErrorMsg = getScreen("field-name-error")
        private val fieldValueErrorMsg = getScreen("field-value-error")
        private val assocationsErrorMsg = getScreen("assocations-error")
        private val horizontalRule = getScreen("horizontal-rule")
    }
}