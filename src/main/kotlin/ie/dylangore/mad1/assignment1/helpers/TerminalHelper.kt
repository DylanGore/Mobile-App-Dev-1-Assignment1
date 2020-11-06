package ie.dylangore.mad1.assignment1.helpers

/**
 * Helper functions relating to the terminal
 */
object TerminalHelper {
    /**
     * Simple function that prints a unicode character to clear the terminal
     *
     */
    fun clearTerminal() {
        print("\u001b[H\u001b[2J")
    }
}