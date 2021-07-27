package com.examples.abbasdgr8.view

/**
 * Represents a single interaction with the user
 *
 * The first arg 'screen' represents the screen that gets displayed to the user
 * The second arg 'prompt' is the text that the user sees asking for input
 * The third arg 'default' is the default value that's expected to be returned back if the user just hits the Return key
 */
data class Interaction(
    val screen: String = "",
    val prompt: String = "",
    val default: String = "Y",
    val showPrompt: Boolean = true,
    val showDefault: Boolean = true
)


enum class Prompts(val text: String) {
    ENTER("Enter?"),
    CHOOSE("Choose an Option"),
    BACK("Back to Menu?"),
    SURE("Are you sure?")
}