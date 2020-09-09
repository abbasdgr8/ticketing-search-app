package com.examples.abbasdgr8.view

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

        private const val splash = """                 
                                    |-------------------------------------------------------|
                                    |---------- Welcome to Ticketing Search App ------------|
                                    |-------------------------------------------------------|
                                    |                                                       |
                                    |                   GENERAL INSTRUCTIONS                | 
                                    |                                                       |
                                    | You can access the option to EXIT from the Main Menu  |
                                    |                                                       |
                                    | Pressing 'Enter' displays the same screen to you again|
                                    |                                                       |
                                    | In order to change the screen, type in the number     |
                                    | corresponding to the option and press 'Enter'         |
                                    |                                                       |
                                    | When no options are presented,                        |
                                    | Enter '.' followed by 'Enter' to Proceed              |
                                    | ------------------------------------------------------|
                                    """
        private const val mainMenu = """                 
                                    |-------------------------------------------------------|
                                    |---------------------- MAIN MENU ----------------------|
                                    |-------------------------------------------------------|
                                    | Select Search Options:                                |
                                    |                                                       |
                                    | Press 1 to go to the Tickets Menu                     |
                                    | Press 2 to go to the Users Menu                       |
                                    | Press 3 to go to the Organizations Menu               |
                                    | Press 4 to EXIT the application                       |
                                    | ------------------------------------------------------|
                                    """

        private const val end = """                 
                                    Press Ctrl+C to exit app
                                    """
    }

}