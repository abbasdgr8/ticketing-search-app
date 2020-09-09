package com.examples.abbasdgr8

import com.examples.abbasdgr8.controller.ViewController
import java.util.*


fun main(args: Array<String>) {
    var input = ""
    while(true) {
        print(controller.processAction(input))
        input = command.nextLine()
    }
}

val controller = ViewController()
val command = Scanner(System.`in`)