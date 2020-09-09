package com.examples.abbasdgr8.view

class ViewCommons {

    companion object {

        fun getFieldNamePrompt(): String {
            return fieldNamePrompt
        }

        fun getFieldValuePrompt(): String {
            return fieldValuePrompt
        }

        private const val fieldNamePrompt = """                 
                                    Please enter name of the field that you want to search by:
                                    """

        private const val fieldValuePrompt = """                 
                                    Please enter value of the field that you want to search by:
                                    """
    }

}