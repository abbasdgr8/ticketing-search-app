package com.examples.abbasdgr8

import com.examples.abbasdgr8.controller.ViewController
import com.examples.abbasdgr8.view.constants.Inputs.*
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.output.TermUi.prompt


class App : CliktCommand() {

    private var userInput = EMPTY.s
    private val controller = ViewController()

    override fun run() {
        /**
         * Continuous feedback loop that facilitates an
         * interactive CLI exchange between user input
         * and app output
         */
        while(true) {
            val i = controller.getInteraction(userInput)
            echo(i.screen)
            userInput = if (i.showPrompt) {
                prompt(text = i.prompt, default = i.default, showDefault = i.showDefault).toString()
            } else {
                YES.s
            }
        }
    }
}


fun main(args: Array<String>) = App().main(args)