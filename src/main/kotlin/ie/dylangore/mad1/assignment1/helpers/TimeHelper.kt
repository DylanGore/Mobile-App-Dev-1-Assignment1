package ie.dylangore.mad1.assignment1.helpers

import ie.dylangore.mad1.assignment1.helpers.LogHelper.logger
import java.text.SimpleDateFormat
import java.util.*

/**
 * Functions to help with formatting timestamps
 */
object TimeHelper {

    /**
     * Convert an IS08601 timestamp string to a Date object
     *
     * @param timestampStr the string to attempt to parse
     * @return the Date object
     */
    fun fromISO8601(timestampStr: String): Date?{
        val timestampFormatMilli = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val timestampFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")

        var date: Date? = null
        try{
            // Try to parse the String assuming it will include milliseconds
            date = timestampFormatMilli.parse(timestampStr)
        }catch (ex: Exception){
            try{
                // Try to parse the string without milliseconds
                date = timestampFormat.parse(timestampStr)
            }catch (ex: Exception){
                logger.warn { "Unable to convert $timestampStr to Date object" }
            }
        }
        // Return the result
        return date
    }

    /**
     * Format a Date object for display to the user
     *
     * @param date tbe date object to format
     */
    fun getDisplayString(date: Date?): String{
        return if (date != null){
            val displayFormat = SimpleDateFormat("HH:mm dd/MM/yyyy")
            displayFormat.format(date)
        }else{
            "Invalid"
        }
    }

    /**
     * Format a Date object for display to the user (with TimeZone)
     *
     * @param date tbe date object to format
     */
    fun getDisplayStringTZ(date: Date?): String{
        return if (date != null){
            val displayFormat = SimpleDateFormat("HH:mm dd/MM/yyyy z")
            displayFormat.format(date)
        }else{
            "Invalid"
        }
    }

}