package com.examples.abbasdgr8.view

import java.io.File
import java.lang.StringBuilder

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

        fun <T> getRecordsWithBanner(records: List<T>): String {
            val sb = StringBuilder()
            records.forEach { record ->
                sb.append(System.lineSeparator())
                sb.append(record.toString())
                sb.append(System.lineSeparator())
            }
            sb.append(System.lineSeparator())
            sb.append("Found ${records.size} result(s)")
            sb.append(System.lineSeparator())

            return sb.toString()
        }

        fun getFieldNameErrorMsg(): String {
            return fieldNameErrorMsg
        }

        fun getFieldValueErrorMsg(): String {
            return fieldValueErrorMsg
        }

        private const val SCREENS_DIR_PATH = "src/main/resources/screens"

        private val fieldNameErrorMsg = getScreen("field-name-error")
        private val fieldValueErrorMsg = getScreen("field-value-error")
    }
}