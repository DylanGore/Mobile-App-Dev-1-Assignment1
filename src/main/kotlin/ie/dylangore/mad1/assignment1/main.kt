package ie.dylangore.mad1.assignment1

import com.github.ajalt.mordant.TermColors
import ie.dylangore.mad1.assignment1.datamodels.Location
import ie.dylangore.mad1.assignment1.weather.Forecasts
import ie.dylangore.mad1.assignment1.weather.Stations
import ie.dylangore.mad1.assignment1.weather.Warnings

// List of saved locations
val locations: MutableList<Location> = mutableListOf(Location(id=1, name="WIT", latitude = 52.2461, longitude = 7.1387))
// The currently selected location (Default to the first entry in the list above)
val selectedLocation:Location = locations[0]

/**
 * Main entrypoint for the console version of the application
 *
 * @param args
 */
fun main(args: Array<String>) {
    logger.info("Launching Console-Only mode")

    var input: Int

    do {
        input = menu()
        when(input) {
            1 -> displayWeatherWarnings()
            2 -> displayObservationStations()
            3 -> displayWeatherForecast()
            4 -> displayManageLocationsMenu()
            0 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != 0)
}

/**
 * Display the main menu
 *
 * @return the option slected by the user
 */
fun menu() : Int {
    // Display the menu (With colour in supported environments)
    with(TermColors()) {
        println()
        println((bold+brightBlue)(String(Character.toChars(0x26C5)) + " Weather Info"))
        val titleStyle = (red + underline)
        println(titleStyle("Main Menu"))
        println("1. List Weather Warnings")
        println("2. List Observation Stations")
        println("3. Get Weather Forecast")
        println("4. Manage Locations")
        println()
        println("0. Exit")
        println()
    }

    // Get the input from the user
    return getIntInput()
}

/**
 * Display a list of current weather warnings
 */
fun displayWeatherWarnings(){
    // Met Éireann Weather Warnings
    val warnings = Warnings.getWeatherWarnings()
    if (warnings.isNotEmpty()){
        println("Weather Warnings (${warnings.size})")
        for ( item in warnings ){
            println(item.headline)
        }
    }else {
        println("There are currently no active weather warnings/advisories")
    }
}

/**
 * Display a list of observation stations with the latest conditions recorded at each
 */
fun displayObservationStations(){
    // Met Éireann Observation Stations
    val stations = Stations.getMetEireannStations()
    if (stations != null){
        println("Met Éireann Observation Stations (${stations.size})")
        for ( item in stations){
            val temperature : String = if (item.temperature != null) item.temperature.toString() else "N/A"
            val humidity : String = if (item.humidity != null) item.humidity.toString() else "N/A"
            val pressure : String = if (item.pressure != null) item.pressure.toString() else "N/A"
            val wind : String = if (item.wind_speed != null) item.wind_speed.toString() else "N/A"
            with(TermColors()) {
                println("${bold(item.location)} - ${String(Character.toChars(0x1F321))}${red(" Temp: $temperature °C")}  ${String(Character.toChars(0x1F4A7))}${green(" Hum: $humidity%")}  ${String(Character.toChars(0x2652))}${blue(" Pres: $pressure hPa")} ${String(Character.toChars(0x1F300))}${white(" Wind: $wind km/h")}")
            }
        }
    }else {
        println("No Observation Stations Found!")
    }
}

/**
 * Display the weather forecast for the currently selected location
 */
fun displayWeatherForecast(){
    // Met.no Weather Forecast
    val forecast = Forecasts.getForecast(selectedLocation.latitude, selectedLocation.longitude)
    println("Forecast for ${selectedLocation.name}: $forecast")
}

/**
 * Display a menu to allow the user to manage saved locations
 */
fun displayManageLocationsMenu(){
    // Display the menu (With colour in supported environments)
    with(TermColors()) {
        println()
        println((bold+brightBlue)(String(Character.toChars(0x26C5)) + " Weather Info"))
        val titleStyle = (red + underline)
        println(titleStyle("Main Menu"))
        println("1. List current locations")
        println("2. Add a new location")
        println("3. Update an existing location")
        println("4. Delete a location")
        println()
        println("0. Return to main menu")
        println()
    }

    // Run a specific function depending on user input
    when(getIntInput()) {
        1 -> listLocations()
        2 -> addLocation()
        3 -> updateLocation()
        4 -> deleteLocation()
//        0 -> println("Back to main menu")
//        else -> println("Invalid Option")
    }
}

/**
 * Function to list saved locations (TODO)
 */
fun listLocations(){
    with(TermColors()) {
        if (locations.isNotEmpty()){
            for(location in locations){
                println("${location.id}. ${location.name} (Lat: ${location.latitude} Lon: ${location.longitude} Alt: ${location.altitude}m)")
            }
        }else{
            println(red("No saved locations!"))
        }
    }
}

/**
 * Function to find a location in the locations list by ID and return that location object.
 * Will return null if no value is found matching the given ID. If the ID is passed as -1, prompt the user to input the ID
 *
 * @param id the ID of the location to find
 */
fun findLocation(id: Long = -1): Location? {
    val locId = if (id > 0) id else getLongInput("Enter the location ID")
    return locations.find { it.id == locId}
}

/**
 * Function to add a new location to the saved locations list (TODO)
 */
fun addLocation(){

    // Define inputs
    var name: String = ""
    var latitude: Double
    var longitude: Double
    var altitude: Int = 0

    // Display the prompts to the user
    with(TermColors()) {
        println(underline("Add a new location"))

        name = getStringInputRepeat("Name")
        latitude = getDoubleInputRepeat("Latitude")
        longitude = getDoubleInputRepeat("Longitude")
        altitude = getIntInput("Altitude", 0)

        locations.add(Location(locations.size.toLong() + 1, name, latitude, longitude, altitude))
        println("Saved $name to locations list")
    }
}

/**
 * Function to update a location in the saved locations list (TODO)
 */
fun updateLocation(){
    // Define inputs
    var name = ""
    var latitude: Double
    var longitude: Double
    var altitude: Int = 0

    // Get the location the user wishes to update
    val location = findLocation()

    with(TermColors()) {
        if (location != null) {
            // Display the prompts to the user
            println(underline("Update location (ID: ${location.id})"))
            name = getStringInputRepeat("Name", location.name)
            latitude = getDoubleInputRepeat("Latitude", location.latitude)
            longitude = getDoubleInputRepeat("Longitude", location.longitude)
            altitude = getIntInput("Altitude", location.altitude)
            // Replace the old data with the new data
            location.name = name
            location.latitude = latitude
            location.longitude = longitude
            location.altitude = altitude
            println("Saved $name to locations list")
        } else {
            println(red("Location not found!"))
        }
    }

}

/**
 * Function to delete a location from the saved locations list (TODO)
 */
fun deleteLocation(){
    // Get the location the user wishes to delete
    val location = findLocation()

    with(TermColors()) {
        if (location != null) {
            // Display the prompts to the user
            val response: Boolean = yesNoPrompt("Are you sure you want to delete ${location.name}")
            if (response){
                locations.remove(location)
                println("Removed ${location.name} from list")
            }else
                println("Location not removed, user declined.")
        } else {
            println(red("Location not found!"))
        }
    }
}

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
                println(red("Invalid! Please enter a text value"))
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
    var oldValStr: String = ""
    if (oldVal.toString().isNotBlank()){
        oldValStr = " [$oldVal]"
    }
    // Prompt the user for input
    with(TermColors()) {
        do {
            print(bold("$prompt$oldValStr: "))
            input = readLine()!!
            if (input.toDoubleOrNull() == null){
                println(red("Invalid! Please enter a double value"))
            }
        } while (input.toDoubleOrNull() == null && input.isNotEmpty())
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
    var oldValStr: String = ""
    if (oldVal.toString().isNotBlank()){
        oldValStr = " [$oldVal]"
    }
    // Prompt the user for input
    with(TermColors()){
        print(bold("$prompt$oldValStr: "))
    }
    // Store the input String
    val input = readLine()!!
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
    // Simple input validation and convert to long
    return if (input.toIntOrNull() != null && input.isNotEmpty()) input.toLong() else 0
}

/**
 * Display a yes or no prompt to the user and return the result as a boolean
 *
 * @param prompt the message to prompt the user with
 * @param default the default option (i.e. is the default option yes or no)
 * @return the result as a boolean
 */
fun yesNoPrompt(prompt: String = "Are you sure", default: Boolean = false): Boolean{
    // Define the default option String to display
    val defaultStr = if (default) "[Y/n]" else "[y/N]"
    with(TermColors()){
        // Prompt the user
        print("$prompt? $defaultStr")
        val input = readLine()!!
        // If the response is empty, return the default option
        if (input.isEmpty()){
            return default
        }else{
            // If the response starts with a Y return true, if it starts with an N return false,
            // if the response starts with anything else, return the default option
            return when {
                input.toLowerCase().startsWith("y") -> true
                input.toLowerCase().startsWith("n") -> false
                else -> default
            }
        }
    }
}