package com.examples.abbasdgr8.view

import java.io.File
import java.lang.StringBuilder

class ViewCommons {

    companion object {

        fun getScreen(fileName: String): String {
            return File("$SCREENS_DIR_PATH/$fileName").readText()
        }

        fun getPrompt(fileName: String): String {
            return File("$PROMPTS_DIR_PATH/$fileName").readText()
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

        fun getFieldNamePrompt(): String {
            return fieldNamePrompt
        }

        fun getFieldValuePrompt(): String {
            return fieldValuePrompt
        }

        fun getFieldNameErrorMsg(): String {
            return fieldNameErrorMsg
        }

        fun getFieldValueErrorMsg(): String {
            return fieldValueErrorMsg
        }

        private const val SCREENS_DIR_PATH = "src/main/resources/screens"
        private const val PROMPTS_DIR_PATH = "src/main/resources/prompts"

        private val fieldNamePrompt = getPrompt("field-name-prompt.txt")
        private val fieldValuePrompt = getPrompt("field-value-prompt.txt")
        private val fieldNameErrorMsg = getScreen("field-name-error.txt")
        private val fieldValueErrorMsg = getScreen("field-value-error.txt")
    }
}