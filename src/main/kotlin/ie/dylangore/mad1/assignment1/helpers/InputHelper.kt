package ie.dylangore.mad1.assignment1.helpers

import com.github.ajalt.mordant.TermColors

/**
 * Helper functions to handle various forms of user input
 */
object InputHelper {
    /**
     * Prompt the user for a String input, repeat the prompt if the entered value is invalid
     *
     * @param prompt the message to prompt the user with
     * @param oldVal the old value of the variable (not required)
     * @return the input as a String
     */
    fun getStringInputRepeat(prompt: String, oldVal: Any = ""): String {
        var input: String
        // Format the old variable for display if it is defined
        var oldValStr = ""
        if (oldVal.toString().isNotBlank()){
            oldValStr = " [$oldVal]"
        }
        // Prompt the user for input
        with(TermColors()) {
            do {
                print(bold("$prompt$oldValStr: "))
                input = readLine()!!
                if (input.isEmpty()){
                    // If the old value is defined and the user presses enter without inputting anything, return the old value
                    if (oldVal.toString().isNotEmpty()){
                        return oldVal as String
                    }else{
                        println(red("Invalid! Please enter a text value"))
                    }
                }
            } while (input.isEmpty())
        }
        return input
    }

    /**
     * Prompt the user for a double input, repeat the prompt if the entered value is invalid
     *
     * @param prompt the message to prompt the user with
     * @param oldVal the old value of the variable (not required)
     * @return the input as a double
     */
    fun getDoubleInputRepeat(prompt: String, oldVal: Any = ""): Double {
        var input: String
        // Format the old variable for display if it is defined
        var oldValStr = ""
        if (oldVal.toString().isNotBlank()){
            oldValStr = " [$oldVal]"
        }
        // Prompt the user for input
        with(TermColors()) {
            do {
                print(bold("$prompt$oldValStr: "))
                input = readLine()!!

                if (input.toDoubleOrNull() == null){
                    // If the old value is defined and the user presses enter without inputting anything, return the old value
                    if (oldVal.toString().isNotEmpty()){
                        if(oldVal.toString().toDoubleOrNull() != null) return oldVal as Double
                    }
                    println(red("Invalid! Please enter a double value"))
                }
            } while (input.toDoubleOrNull() == null && input.isEmpty())
        }

        return input.toDouble()
    }

    /**
     * Function that reads the input from standard input and converts it to an integer
     * If the input is invalid, return 0
     *
     * @param prompt the message to prompt the user with
     * @param oldVal the previous value of the variable (not required)
     * @return the input as an integer
     */
    fun getIntInput(prompt: String = "Select an option", oldVal: Any = ""): Int{
        // Format the old variable for display if it is defined
        var oldValStr = ""
        if (oldVal.toString().isNotBlank()){
            oldValStr = " [$oldVal]"
        }
        // Prompt the user for input
        with(TermColors()){
            print(bold("$prompt$oldValStr: "))
        }

        // Store the input String
        val input = readLine()!!

        // If the old value is defined and the user presses enter without inputting anything, return the old value
        if (input.isBlank() && oldVal.toString().isNotEmpty()){
            if(oldVal.toString().toIntOrNull() != null) return oldVal as Int
        }

        // Simple input validation and convert to Integer option
        return if (input.toIntOrNull() != null && input.isNotEmpty()) input.toInt() else 0
    }

    /**
     * Function that reads the input from standard input and converts it to an long
     * If the input is invalid, return 0
     *
     * @param prompt the message to prompt the user with
     * @param oldVal the previous value of the variable (not required)
     * @return the input as an integer
     */
    fun getLongInput(prompt: String = "Select an option", oldVal: Any = ""): Long{
        // Format the old variable for display if it is defined
        var oldValStr = ""
        if (oldVal.toString().isNotBlank()){
            oldValStr = " [$oldVal]"
        }
        // Prompt the user for input
        with(TermColors()){
            print(bold("$prompt$oldValStr: "))
        }
        // Store the input String
        val input = readLine()!!

        // If the old value is defined and the user presses enter without inputting anything, return the old value
        if (input.isBlank() && oldVal.toString().isNotEmpty()){
            if(oldVal.toString().toLongOrNull() != null) return oldVal as Long
        }

        // Simple input validation and convert to long
        return if (input.toLongOrNull() != null && input.isNotEmpty()) input.toLong() else 0
    }

    /**
     * Display a yes or no prompt to the user and return the result as a boolean
     *
     * @param prompt the message to prompt the user with
     * @param default the default option (i.e. is the default option yes or no)
     * @return the result as a boolean
     */
    @Suppress("RedundantWith")
    fun yesNoPrompt(prompt: String = "Are you sure", default: Boolean = false): Boolean{
        // Define the default option String to display
        val defaultStr = if (default) "[Y/n]" else "[y/N]"
        with(TermColors()){
            // Prompt the user
            print("$prompt? $defaultStr ")
            val input = readLine()!!
            // If the response is empty, return the default option
            return if (input.isEmpty()){
                default
            }else{
                // If the response starts with a Y return true, if it starts with an N return false,
                // if the response starts with anything else, return the default option
                when {
                    input.toLowerCase().startsWith("y") -> true
                    input.toLowerCase().startsWith("n") -> false
                    else -> default
                }
            }
        }
    }
}