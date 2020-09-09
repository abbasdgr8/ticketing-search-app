package com.examples.abbasdgr8

import com.examples.abbasdgr8.controller.ViewController
import java.util.*


fun main(args: Array<String>) {

    /**
     * Continuous feedback loop that facilitates an
     * interactive CLI exchange between user input
     * and app output
     */
    while(true) {
        print(controller.processAction(input))
        input = command.nextLine()
    }
}

val controller = ViewController()
val command = Scanner(System.`in`)
var input = ""