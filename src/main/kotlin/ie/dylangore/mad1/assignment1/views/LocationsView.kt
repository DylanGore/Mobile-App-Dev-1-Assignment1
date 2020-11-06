package ie.dylangore.mad1.assignment1.views

import com.github.ajalt.mordant.TermColors
import ie.dylangore.mad1.assignment1.helpers.InputHelper
import ie.dylangore.mad1.assignment1.helpers.TerminalHelper.clearTerminal
import ie.dylangore.mad1.assignment1.helpers.ValidationHelper
import ie.dylangore.mad1.assignment1.locations
import ie.dylangore.mad1.assignment1.locationsController
import ie.dylangore.mad1.assignment1.models.Location
import ie.dylangore.mad1.assignment1.selectedLocation

/**
 * View class for the locations menu
 */
class LocationsView {
    /**
     * Display the main menu
     *
     * @return the option selected by the user
     */
    fun menu(): Int {
        clearTerminal()
        // Display the menu (With colour in supported environments)
        with(TermColors()) {
            println()
            println((bold+brightBlue)(String(Character.toChars(0x26C5)) + " Weather Info"))
            val titleStyle = (red + underline)
            println(titleStyle("Locations Menu"))
            println("1. List current locations")
            println("2. Add a new location")
            println("3. Update an existing location")
            println("4. Delete a location")
            println("5. Select forecast location")
            println()
            println("0. Return to main menu")
            println()
        }

        // Get the input from the user
        return InputHelper.getIntInput()
    }

    /**
     * Function to list saved locations
     */
    fun listLocations() {
        clearTerminal()
        with(TermColors()) {
            if (locations.findAll().isNotEmpty()) {
                for (location in locations.findAll()) {
                    println("${location.id}. ${location.name} (Lat: ${location.latitude} Lon: ${location.longitude} Alt: ${location.altitude}m)")
                }
            } else {
                println(red("No saved locations!"))
            }
        }
    }

    /**
     * Prompt the user for information to add a new location
     * Validation is handled via the InputHelper functions
     *
     * @param location empty location to store the new data
     * @return the new location
     */
    fun addLocationData(location: Location): Boolean {
        // Display the prompts to the user
        with(TermColors()) {
            println(underline("Add a new location"))

            location.name = InputHelper.getStringInputRepeat("Name")
            location.latitude = InputHelper.getDoubleInputRepeat("Latitude")
            location.longitude = InputHelper.getDoubleInputRepeat("Longitude")
            location.altitude = InputHelper.getIntInput("Altitude", 0)

            return ValidationHelper.validateLocation(location)
        }
    }

    /**
     * Prompt the user for information to update an existing
     * Validation is handled via the InputHelper functions
     *
     * @param location copy of existing location to store updated values
     * @return boolean reflecting the validation state of the updated location
     */
    fun updateLocationData(location: Location): Boolean {
        // Display the prompts to the user
        with(TermColors()) {
            // Display the prompts to the user along with the current values
            println(underline("Update location (ID: ${location.id})"))
            location.name = InputHelper.getStringInputRepeat("Name", location.name)
            location.latitude = InputHelper.getDoubleInputRepeat("Latitude", location.latitude)
            location.longitude = InputHelper.getDoubleInputRepeat("Longitude", location.longitude)
            location.altitude = InputHelper.getIntInput("Altitude", location.altitude)

            return ValidationHelper.validateLocation(location)
        }
    }

    /**
     * Prompt the user for an ID to delete an existing location
     * Validation is handled via the InputHelper functions
     *
     * @param id the ID of the location to delete
     * @return boolean representing the choice to delete the specified location
     */
    fun deleteLocationData(id: Long): Boolean {
        clearTerminal()
        val location: Location? = locations.findOne(id)
        with(TermColors()) {
            if (location != null) {
                // Display the prompts to the user
                val response: Boolean = InputHelper.yesNoPrompt("Are you sure you want to delete ${location.name}")
                if (response){
                    return true
                }else
                    println("Location will not be removed, user declined.")
                    return false
            } else {
                println(red("Location not found!"))
                return false
            }
        }
    }

    /**
     * Display the select location menu
     */
    fun displaySelectLocationMenu() {
        // Display the prompts to the user
        with(TermColors()) {
            println(underline("Select location"))
            displaySelectedLocation()
            listLocations()
        }
    }

    /**
     * Display the currently selected location
     *
     */
    fun displaySelectedLocation(){
        // Get the location name
        val locationName = selectedLocation?.name ?: "No location selected"
        with(TermColors()){
            println(bold("Selected Location: ") + locationName)
        }
    }

    /**
     * Prompt the user to enter a location ID
     *
     * @return the entered ID
     */
    fun getLocationId(): Long{
        return InputHelper.getLongInput("Enter the location ID")
    }
}