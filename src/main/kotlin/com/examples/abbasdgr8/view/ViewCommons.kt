package com.examples.abbasdgr8.view

class ViewCommons {

    companion object {

        fun getFieldNamePrompt(): String {
            return fieldNamePrompt
        }

        fun getFieldValuePrompt(): String {
            return fieldValuePrompt
        }

        fun getErrorMsg(): String {
            return errorMsg
        }

        private const val fieldNamePrompt = """                 
                                    Please enter name of the field that you want to search by:
                                    """

        private const val fieldValuePrompt = """                 
                                    Please enter value of the field that you want to search by:
                                    """

        private const val errorMsg = """                 
                                    An error occured with your search entry. Please review the
                                    field names and their data types and try again.
                                    
                                    Enter '.' followed by 'Enter' to Proceed
                                    
                                    
                                    """
    }

}