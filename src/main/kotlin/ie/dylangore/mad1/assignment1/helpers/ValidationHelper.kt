package ie.dylangore.mad1.assignment1.helpers

import ie.dylangore.mad1.assignment1.logger
import ie.dylangore.mad1.assignment1.models.Location

/**
 * Helper functions related to data validation
 */
object ValidationHelper {
    /**
     * Check that a given location contains valid data
     *
     * @param location the location to be checked
     * @return boolean reflecting validation status
     */
    fun validateLocation(location: Location): Boolean {
        // Ensure the ID is positive
        if (location.id < 0){
            logger.error { "Validator Error: Location ID invalid (${location.id})" }
            return false
        }

        // Ensure name has a defined value
        if(location.name.isEmpty()){
            logger.error { "Validator Error: Location name invalid (${location.name})" }
            return false
        }

        // Ensure longitude is within valid bounds
        if (location.latitude < -90.0 && location.latitude > 90.0){
            logger.error { "Validator Error: Location latitude invalid (${location.latitude})" }
            return false
        }

        // Ensure latitude is with valid bounds
        if (location.longitude < -180.0 && location.longitude > 180.0){
            logger.error { "Validator Error: Location longitude invalid (${location.longitude})" }
            return false
        }

        // Ensure altitude is positive
        if (location.altitude < 0){
            logger.error { "Validator Error: Location altitude invalid (${location.altitude})" }
            return false
        }

        // If nothing above returns false, the data is valid
        logger.info{ "Validator: OK"}
        return true
    }
}