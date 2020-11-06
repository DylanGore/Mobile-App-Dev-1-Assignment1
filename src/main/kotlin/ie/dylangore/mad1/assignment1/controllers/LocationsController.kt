package ie.dylangore.mad1.assignment1.controllers

import com.github.ajalt.mordant.TermColors
import ie.dylangore.mad1.assignment1.helpers.TerminalHelper.clearTerminal
import ie.dylangore.mad1.assignment1.locations
import ie.dylangore.mad1.assignment1.locationsView
import ie.dylangore.mad1.assignment1.models.Location

/**
 * Controller class for the locations menu
 */
class LocationsController {

    /**
     * Display a menu to allow the user to manage saved locations
     */
    fun locationsMenu(){
        // Run a specific function depending on user input
        when(locationsView.menu()) {
            1 -> locationsView.listLocations()
            2 -> addLocation()
            3 -> updateLocation()
            4 -> deleteLocation()
            0 -> println("Back to main menu")
            else -> println("Invalid Option")
        }
    }

    /**
     * Function to find a location in the locations list by ID and return that location object.
     * Will return null if no value is found matching the given ID. If the ID is passed as -1, prompt the user to input the ID
     *
     * @param id the ID of the location to find
     */
    private fun findLocation(id: Long = -1): Location? {
        val locId = if (id > 0) id else locationsView.getLocationId()
        return locations.findOne(locId)
    }

    /**
     * Add a new location to the locations list.
     * Validation is handled in the LocationStore.
     */
    private fun addLocation(){
        val location = Location()
        // If the user has input valid data, add the new location
        if (locationsView.addLocationData(location)){
            locations.add(location)
        }
    }

    /**
     * Function to update a location in the saved locations list
     */
    private fun updateLocation(){
        clearTerminal()
        locationsView.listLocations()
        // Get the location the user wishes to update
        val location = findLocation()

        with(TermColors()) {
            if (location != null) {

                // Update the stored location
                if (locationsView.updateLocationData(location)){
                    locations.update(location)
                }
                println("Saved ${location.name} to locations list")
            } else {
                println(red("Location not found!"))
            }
        }

    }

    /**
     * Delete a location from the list
     * Validation is handled in the LocationStore.
     */
    private fun deleteLocation(){
        // Display the current location list
        locationsView.listLocations()
        // Prompt the user to input a location, if it is valid and confirmed by the user, delete it
        val id = locationsView.getLocationId()
        if (locationsView.deleteLocationData(id)){
            locations.delete(id)
        }
    }
}