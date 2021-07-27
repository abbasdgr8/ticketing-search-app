package com.examples.abbasdgr8.view

import com.examples.abbasdgr8.view.ViewCommons.Companion.getScreen

class IndexView {

    companion object {

        fun getSplashScreen(): String {
            return splash
        }

        fun getMainMenu(): String {
            return mainMenu
        }

        fun getEnd(): String {
            return end
        }

        private val splash = getScreen("splash.txt")
        private val mainMenu = getScreen("main-menu.txt")
        private val end = getScreen("end.txt")
    }
}