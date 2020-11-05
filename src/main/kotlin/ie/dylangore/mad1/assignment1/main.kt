package ie.dylangore.mad1.assignment1

import com.github.ajalt.mordant.TermColors
import ie.dylangore.mad1.assignment1.datamodels.Location
import ie.dylangore.mad1.assignment1.weather.Forecasts
import ie.dylangore.mad1.assignment1.weather.Stations
import ie.dylangore.mad1.assignment1.weather.Warnings

// List of saved locations
val locations: MutableList<Location> = mutableListOf(Location(id=0, name="WIT", latitude = 52.2461, longitude = 7.1387))
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
    var option : Int
    var input: String? = null

    // Display the menu (With colour in supported environments)
    with(TermColors()) {
        println()
        println((bold+brightBlue)(String(Character.toChars(0x26C5)) + " Weather Info"))
        val titleStyle = (red + underline)
        println(titleStyle("Main Menu"))
        println(" 1. List Weather Warnings")
        println(" 2. List Observation Stations")
        println(" 3. Get Weather Forecast")
        println(" 4. Manage Locations")
        println()
        println(" 0. Exit")
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
        println("""Weather Warnings (${warnings.size})""")
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
        println(" 1. List current locations")
        println(" 2. Add a new location")
        println(" 3. Delete a location")
        println()
        println(" 0. Return to main menu")
    }

    // Run a specific function depending on user input
    when(getIntInput()) {
        1 -> listLocations()
        2 -> addLocation()
        3 -> deleteLocation()
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
            for ((index, location) in locations){
                println("${index + 1}. $location")
            }
        }else{
            println(red("No saved locations!"))
        }
    }
}

/**
 * Function to add a new location to the saved locations list (TODO)
 */
fun addLocation(){
    println("Add a new location")
}

/**
 * Function to update a location in the saved locations list (TODO)
 */
fun updateLocation(){
    println("Update an existing location")
}

/**
 * Function to delete a location from the saved locations list (TODO)
 */
fun deleteLocation(){
    println("Delete a location")
}

/**
 * Function that reads the input from standard input and converts it to an integer
 *
 * @return the input as an integer
 */
fun getIntInput(): Int{
    // Prompt the user for input
    println()
    print("Select an option : ")
    // Store the input String
    val input = readLine()!!
    // Simple input validation and convert to Integer option
    return if (input.toIntOrNull() != null && input.isNotEmpty()) input.toInt() else 0
}