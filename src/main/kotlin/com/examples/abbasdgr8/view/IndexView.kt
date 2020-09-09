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
                                    |*******************************************************|
                                    |                                                       |
                                    |                   GENERAL INSTRUCTIONS                | 
                                    |                                                       |
                                    | You can access the option to EXIT from the Main Menu  |
                                    |                                                       |
                                    | Pressing 'Enter' displays the same screen to you again|
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
                                    | Type 1 to go to the Tickets Menu                      |
                                    | Type 2 to go to the Users Menu                        |
                                    | Type 3 to go to the Organizations Menu                |
                                    | Type 4 to EXIT the application                        |
                                    | ------------------------------------------------------|
                                    
                                    """

        private const val end = """                 
                                    |-------------------------------------------------------|
                                    | You have chosen to EXIT the app. Exiting gracefully...|
                                    |-------------------------------------------------------|
                                    """
    }

}