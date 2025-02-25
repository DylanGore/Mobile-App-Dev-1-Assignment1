package ie.dylangore.mad1.assignment1.controllers

import ie.dylangore.mad1.assignment1.*
import ie.dylangore.mad1.assignment1.helpers.FileHelper.delete
import ie.dylangore.mad1.assignment1.helpers.LogHelper.logger
import ie.dylangore.mad1.assignment1.helpers.TerminalHelper.clearTerminal
import ie.dylangore.mad1.assignment1.models.Location
import ie.dylangore.mad1.assignment1.sampledata.SampleData
import ie.dylangore.mad1.assignment1.weather.Warnings

/**
 * Controller class for the main menu
 */
class MenuController {
    /**
     * Called when the controller is initialised
     */
    init {
        logger.info("Launching Console-Only mode")

        // Set the selected location to the first entry in the list
        if (locations.findAll().isNotEmpty()){
            selectedLocation = locations.findAll().first()
        }
    }

    /**
     * Process the user's menu selection to perform the correct action
     */
    fun start() {
        var input: Int

        clearTerminal()

        do {
            input = menu()
            when(input) {
                1 -> displayWeatherWarnings()
                2 -> displayObservationStations()
                3 -> displayWeatherForecast()
                4 -> locationsController.locationsMenu()
                0 -> println("Exiting App")
                -99 -> loadSampleData()
                else -> println("Invalid Option")
            }
            println()
        } while (input != 0)
    }


    /**
     * Display the menu to the user
     *
     * @return the selected menu option
     */
    fun menu() :Int { return menuView.menu() }

    /**
     * Display a list of current weather warnings
     */
    private fun displayWeatherWarnings(){
        menuView.displayWeatherWarnings()
    }

    /**
     * Display a list of observation stations with the latest conditions recorded at each
     */
    private fun displayObservationStations(){
        menuView.displayObservationStations()
    }

    /**
     * Display the weather forecast for the currently selected location
     */
    private fun displayWeatherForecast(){
       menuView.displayWeatherForecast()
    }

    private fun loadSampleData(){
        clearTerminal()
        println("Deleting existing data")
        // Remote the JSON file if it exists
        delete("locations.json")
        // Empty the list stored in memory
        locations.empty()
        println("Loading sample data")
        // Add WIT
        locations.add(Location(-1, "WIT", 52.2461, 7.1387))
        // Display sample warnings
        menuView.displayWeatherWarnings(Warnings.getWeatherWarnings(SampleData.two_warnings))
    }
}